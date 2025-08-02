package com.spring.lukeapiserver.global.security.jwt.exception;

import com.spring.lukeapiserver.global.exception.BusinessException;
import com.spring.lukeapiserver.global.security.jwt.exception.error.JwtTokenError;

// JWT 토큰이 잘못된 경우에 발생하는 예외 클래스
// BusinessException을 상속받아 사용자 정의 예외를 구현
public class TokenTypeException extends BusinessException {

    // TokenTypeException의 단일 인스턴스를 제공하기 위한 상수
    public static final TokenTypeException EXCEPTION = new TokenTypeException();

    // 기본 생성자: 외부에서 생성하지 못하도록 private으로 제한
    private TokenTypeException() {
        // JwtTokenError 열거형의 JWT_TOKEN_ERROR 값을 사용하여 상위 클래스의 생성자를 호출
        super(JwtTokenError.JWT_TOKEN_ERROR);
    }
}