package com.spring.lukeapiserver.global.exception.error;

import org.springframework.http.HttpStatus;

// 에러 반환 프로퍼티 인터페이스
public interface ErrorProperty {

    // HTTP 상태 코드 반환
    HttpStatus getStatus();  // 예: HttpStatus.INTERNAL_SERVER_ERROR

    // 에러 메시지 반환
    String getMessage();  // 예: "서버에 오류가 발생하였습니다."
}
