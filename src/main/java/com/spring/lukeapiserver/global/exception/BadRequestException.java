package com.spring.lukeapiserver.global.exception;

import com.spring.lukeapiserver.global.exception.error.ErrorCode;

// 400 에러 코드 기본 예외 처리 클래스
public class BadRequestException extends BusinessException {

    public static final BadRequestException EXCEPTION = new BadRequestException();

    private BadRequestException() {
        super(ErrorCode.BAD_REQUEST);
    }

}
