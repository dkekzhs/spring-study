package com.dongjae.skeleton_server.member.service;

import com.dongjae.skeleton_server.common.util.JwtUtil;
import com.dongjae.skeleton_server.member.domain.Member;
import com.dongjae.skeleton_server.member.dto.MemberDto;
import com.dongjae.skeleton_server.member.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

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

}
