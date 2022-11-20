package com.example.jumptospringboot.User.Service;

import com.example.jumptospringboot.Common.Exception.DataNotFoundException;
import com.example.jumptospringboot.User.Domain.UserSite;
import com.example.jumptospringboot.User.Domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserSite CreateUser(UserSite user){
        this.userRepository.save(user);
        return user;
    }
    public UserSite getUser(String username){
        Optional<UserSite> byusername = this.userRepository.findByusername(username);
        if(byusername.isPresent()){
            return byusername.get();
        }
        else{
            throw new DataNotFoundException("유저 못찾음");
        }
    }


}
