package com.spring.lukeapiserver.global.infra.google.exception.error;

import com.spring.lukeapiserver.global.exception.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GoogleError implements ErrorProperty {

    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "계정을 찾을 수 없습니다."); // 구글 계정이 존재하지 않는 경우

    private final HttpStatus status;
    private final String message;

}


