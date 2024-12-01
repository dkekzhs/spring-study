package com.dongjae.skeleton_server.common.dto;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    NOT_VERIFIED_GOOGLE_TOKEN(true, 1001, "구글 토큰의 올바르지 않습니다"),
    NOT_FOUND_USER(true, 1002, "유저를찾지 못했습니다"),
    OOPS(false, 5000, "Oops...");


    private final boolean isSuccess;
    private final int code;
    private final String message;


    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
