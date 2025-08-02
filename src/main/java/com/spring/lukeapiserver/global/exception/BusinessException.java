package com.spring.lukeapiserver.global.exception;

import com.spring.lukeapiserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 사용자 정의 에러 부모 클래스 (사용자 정의 에러 생성 시 해당 클래스 상속)
@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException {

    private final ErrorProperty error;  // 에러 상태 코드와 메시지를 담는 객체

}
