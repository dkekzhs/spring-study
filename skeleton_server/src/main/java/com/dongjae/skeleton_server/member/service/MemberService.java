package com.dongjae.skeleton_server.member.service;

import com.dongjae.skeleton_server.common.exception.BaseException;
import com.dongjae.skeleton_server.member.domain.Member;
import com.dongjae.skeleton_server.member.dto.MemberDto;
import com.dongjae.skeleton_server.member.dto.TokenRequest;
import com.dongjae.skeleton_server.member.dto.TokenResponse;

import java.util.Map;

public interface MemberService {

    MemberDto loginGoogle(TokenRequest tokenRequest);

    Member login(MemberDto memberDto) throws BaseException;

    Member findById(int id) throws BaseException;
}
