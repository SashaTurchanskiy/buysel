package com.example.buysell.services.impl;

import com.example.buysell.model.User;
import com.example.buysell.model.enums.Role;
import com.example.buysell.repos.UserRepo;
import com.example.buysell.services.UserService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;

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
        if (!StringUtils.isEmpty(user.getEmail())){
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
    public boolean activateUser(String code) {
        User user = UserRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);

        userRepo.save(user);
        return true;
    }

}
