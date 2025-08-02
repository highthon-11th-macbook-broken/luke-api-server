package com.spring.lukeapiserver.global.exception;

import com.spring.lukeapiserver.global.exception.error.ErrorCode;

public class NotFoundException extends BusinessException {

    public static final NotFoundException EXCEPTION = new NotFoundException();

    private NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }

}
