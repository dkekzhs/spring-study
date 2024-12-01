package com.dongjae.skeleton_server.common.exception;

import com.dongjae.skeleton_server.common.dto.BaseResponse;
import com.dongjae.skeleton_server.common.dto.BaseResponseStatus;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(BaseException.class)
    public BaseResponse<BaseResponseStatus> baseException(BaseException e) {
        log.warn("Handle CommonException: {}", e.getStatus());
        return new BaseResponse<>(e.getStatus());
    }
    @ExceptionHandler(Exception.class)
    protected BaseResponse<BaseResponseStatus> handleException(Exception e) {
        log.warn("Exception: {}", e.getMessage());
        return new BaseResponse<>(BaseResponseStatus.OOPS);
    }
}
