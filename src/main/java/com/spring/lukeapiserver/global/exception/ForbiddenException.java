package com.spring.lukeapiserver.global.exception;

import com.spring.lukeapiserver.global.exception.error.ErrorCode;

public class ForbiddenException extends BusinessException {

    public static final ForbiddenException EXCEPTION = new ForbiddenException();

    private ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }

}
