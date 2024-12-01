package com.dongjae.skeleton_server.user;

import com.dongjae.skeleton_server.common.dto.BaseResponse;
import com.dongjae.skeleton_server.common.dto.BaseResponseStatus;
import com.dongjae.skeleton_server.user.dto.TokenRequest;
import com.dongjae.skeleton_server.user.dto.TokenResponse;
import com.dongjae.skeleton_server.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginController {

    private UserService userService;

    @PostMapping("/google")
    public BaseResponse<?> handleGoogleToken(@RequestBody TokenRequest tokenRequest) {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, tokenRequest);
    }


    @PostMapping("/login/test")
    public BaseResponse<?> loginTest(@RequestBody Map<String, String> map) {
        TokenResponse login = userService.login(map);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, login);
    }

}
