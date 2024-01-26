package com.example.buysell.services;

import com.example.buysell.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public boolean createUser(User user);

}
