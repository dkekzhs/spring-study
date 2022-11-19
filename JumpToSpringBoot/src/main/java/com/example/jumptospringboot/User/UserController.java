package com.example.jumptospringboot.User;

import com.example.jumptospringboot.User.Domain.UserSite;
import com.example.jumptospringboot.User.Dto.UserCreateRequestDto;
import com.example.jumptospringboot.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signup(UserCreateRequestDto userCreateRequestDto) {
        return "signup_form";
    }
    @PostMapping("/signup")
    public String signup(@Valid UserCreateRequestDto userCreateRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userCreateRequestDto.getPassword1().equals(userCreateRequestDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }
        try{
            UserSite build = UserSite.builder().password(passwordEncoder.encode(userCreateRequestDto.getPassword1()))
                    .email(userCreateRequestDto.getEmail())
                    .username(userCreateRequestDto.getUsername()).build();
                userService.CreateUser(build);
        }catch (DataIntegrityViolationException e ){
            e.printStackTrace();
            bindingResult.reject("signupFailed","이미 등록된 사용자 입니다.");
            return "signup_form";

        }catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("singupFailed",e.getMessage());
            return "signup_form";

        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login_form";
    }
}
