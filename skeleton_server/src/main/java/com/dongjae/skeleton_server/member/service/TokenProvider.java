package com.dongjae.skeleton_server.member.service;

import com.dongjae.skeleton_server.common.util.JwtUtil;
import com.dongjae.skeleton_server.member.domain.Member;
import com.dongjae.skeleton_server.member.dto.MemberDto;
import com.dongjae.skeleton_server.member.dto.TokenRequest;
import com.dongjae.skeleton_server.member.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenProvider {
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    public TokenResponse loginAndTokenCreate(MemberDto memberDto) {
        Member login = memberService.login(memberDto);
        String refreshToken = jwtUtil.generateRefreshToken(login.getName());
        String accessToken = jwtUtil.generateAccessToken(login.getName());
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenResponse googleLoginAndTokenCreate(TokenRequest tokenRequest) {
        MemberDto memberDto = memberService.loginGoogle(tokenRequest);
        String accessToken = jwtUtil.generateAccessToken(memberDto.getId());
        String refreshToken = jwtUtil.generateRefreshToken(memberDto.getId());
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
