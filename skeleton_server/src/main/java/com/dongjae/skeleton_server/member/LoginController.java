package com.dongjae.skeleton_server.member;

import com.dongjae.skeleton_server.common.dto.BaseResponse;
import com.dongjae.skeleton_server.common.dto.BaseResponseStatus;
import com.dongjae.skeleton_server.member.domain.Member;
import com.dongjae.skeleton_server.member.dto.MemberDto;
import com.dongjae.skeleton_server.member.dto.TokenRequest;
import com.dongjae.skeleton_server.member.dto.TokenResponse;
import com.dongjae.skeleton_server.member.service.MemberService;
import com.dongjae.skeleton_server.member.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/google")
    public BaseResponse<?> handleGoogleToken(@RequestBody TokenRequest tokenRequest) {
        TokenResponse tokenResponse = tokenProvider.googleLoginAndTokenCreate(tokenRequest);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, tokenResponse);
    }


    @PostMapping("/sign")
    public BaseResponse<?> loginTest(@RequestBody MemberDto memberDto) {
        TokenResponse tokenResponse = tokenProvider.loginAndTokenCreate(memberDto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, tokenResponse);
    }

}
