package com.example.buysell.services;

import com.example.buysell.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {

    public boolean createUser(User user);
    public void sendMessage(User user);

    User getUserByPrincipal(Principal principal);

    public boolean activateUser(String code);
    public List<User> list();

    void banUser(Long id);

    void changeUserRole(User user, Map<String, String> form);

}
