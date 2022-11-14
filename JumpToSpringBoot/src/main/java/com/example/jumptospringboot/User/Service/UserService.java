package com.example.jumptospringboot.User.Service;

import com.example.jumptospringboot.User.Domain.User;
import com.example.jumptospringboot.User.Domain.UserRepository;
import com.example.jumptospringboot.security.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;


    public User CreateUser(User user){
        this.userRepository.save(user);
        return user;
    }
}
