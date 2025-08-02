package com.spring.lukeapiserver.global.security.jwt.exception.error;

import com.spring.lukeapiserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

// jwt 인증 로직 관련 비즈니스 에러 정의
@Getter
@RequiredArgsConstructor
public enum JwtTokenError implements ErrorProperty {

    JWT_TOKEN_ERROR(HttpStatus.BAD_REQUEST, "잘못된 타입"); // 헤더에 잘못 된 토큰을 받은 경우

    private final HttpStatus status;
    private final String message;

}
