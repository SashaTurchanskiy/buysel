package com.example.buysell.repos;

import com.example.buysell.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    static User findByActivationCode(String code) {
        return null;
    }
}
