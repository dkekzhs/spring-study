package com.dongjae.skeleton_server.common.dto;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {

    //success
    SUCCESS(true, 1000, "요청에 성공하였습니다."),



    //verification error
    NOT_VERIFIED_GOOGLE_TOKEN(true, 1001, "구글 토큰의 올바르지 않습니다."),
    EXPIRES_ACCESS_TOKEN(true, 1002, "엑세스토큰이 만료되었습니다."),
    NOT_FOUND_USER(true, 1002, "유저를찾지 못했습니다"),
    NOT_GET_GOOGLE_TOKEN(true, 1004, "토큰을 받아오는 중 오류가 발생했습니다"),
    NOT_VALUABLE_INPUT(true, 2005, "올바르지 않은 데이터입니다"),



    //server error

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
