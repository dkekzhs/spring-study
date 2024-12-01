package com.dongjae.skeleton_server.user.service;

import com.dongjae.skeleton_server.common.exception.BaseException;
import com.dongjae.skeleton_server.user.domain.Member;
import com.dongjae.skeleton_server.user.dto.TokenRequest;
import com.dongjae.skeleton_server.user.dto.TokenResponse;

import java.util.Map;

public interface UserService {

    TokenResponse loginGoogle(TokenRequest tokenRequest);

    TokenResponse login(Map<String, String> map) throws BaseException;

    Member findById(int id) throws BaseException;
}
