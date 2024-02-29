package com.example.buysell.services.impl;

import com.example.buysell.model.Image;
import com.example.buysell.model.User;
import com.example.buysell.model.enums.Role;
import com.example.buysell.repos.UserRepo;
import com.example.buysell.services.UserService;
import com.example.buysell.utils.ImageConverter;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    private final ImageConverter imageConverter;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepo.findByEmail(email) != null)
            return false;
        user.setActive(true);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        userRepo.save(user);
        sendMessage(user);
        return true;
    }

    @Override
    public void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Buy-sell. Please, visit next link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), message);
        }
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null)
            return new User();
        return userRepo.findByEmail(principal.getName());

    }

    @Override
    public boolean activateUser(String code) {
        User user = UserRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);

        userRepo.save(user);
        return true;
    }

    @Override
    public List<User> list() {
        return userRepo.findAll();
    }

    @Override
    public void banUser(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user != null) {
            boolean currentStatus = user.isActive();
            user.setActive(!currentStatus); // Змінюємо статус на протилежний
            if (!currentStatus) {
                log.info("User with id = {} has been banned; email: {}", user.getId(), user.getEmail());
            } else {
                log.info("User with id = {} has been unbanned; email: {}", user.getId(), user.getEmail());
            }
            userRepo.save(user); // Зберігаємо зміни в базі даних
        } else {
            log.error("User with id = {} not found", id);
        }

    }

    @Override
    public void changeUserRole(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }


    }





