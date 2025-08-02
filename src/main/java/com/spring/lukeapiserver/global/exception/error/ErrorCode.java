package com.spring.lukeapiserver.global.exception.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

// 기본 에러 반환 타입
@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ErrorProperty {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생하였습니다."),  // 500 내부 서버 오류
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),  // 400 잘못된 요청
    NOT_FOUND(HttpStatus.NOT_FOUND, "리소스를 찾을 수 없습니다."),  // 404 리소스를 찾을 수 없음
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다.");  // 403 권한 없음

    private final HttpStatus status;  // HTTP 상태 코드
    private final String message;  // 에러 메시지

}