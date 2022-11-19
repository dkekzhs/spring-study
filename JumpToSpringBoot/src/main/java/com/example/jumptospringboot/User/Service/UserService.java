package com.example.jumptospringboot.User.Service;

import com.example.jumptospringboot.User.Domain.UserSite;
import com.example.jumptospringboot.User.Domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;


    public UserSite CreateUser(UserSite user){
        this.userRepository.save(user);
        return user;
    }


}
