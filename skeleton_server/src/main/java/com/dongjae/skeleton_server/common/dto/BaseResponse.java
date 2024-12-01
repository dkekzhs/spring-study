package com.dongjae.skeleton_server.common.dto;

import lombok.Getter;

@Getter
public class BaseResponse <T> {
    private final Boolean isSuccess;
    private final String message;
    private final int code;
    private T output;


    public BaseResponse(T output) {
        this.isSuccess = BaseResponseStatus.SUCCESS.isSuccess();
        this.message = BaseResponseStatus.SUCCESS.getMessage();
        this.code = BaseResponseStatus.SUCCESS.getCode();
        this.output = output;
    }
    public BaseResponse(BaseResponseStatus status, T output) {
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
        this.output = output;
    }
    public BaseResponse(BaseResponseStatus status){
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }


}
