package com.spring.lukeapiserver.global.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 유효성 검증 예외를 처리하는 클래스
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)  // 우선순위를 가장 높게 설정
public class ValidationExceptionAdvice {

    /**
     * @Valid 검증 실패 예외를 처리하는 메서드
     *
     * @param exception MethodArgumentNotValidException 예외 객체
     * @return ErrorResponse 에러 응답 객체
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)  // @Valid 검증 실패 시 발생하는 예외 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // HTTP 상태 코드를 BAD_REQUEST로 설정
    public ErrorResponse catchValidationException(MethodArgumentNotValidException exception) {
        // 첫 번째 오류 메시지 가져오기
        String message = exception
                .getBindingResult()
                .getAllErrors()
                .getFirst()
                .getDefaultMessage();

        // ErrorResponse 반환
        return ErrorResponse.of(message);
    }

    /**
     * 에러 응답 객체
     *
     * @param status  HTTP 상태 코드
     * @param message 에러 메시지
     */
        private record ErrorResponse(int status, String message) {
            /**
             * 에러 메시지를 기반으로 ErrorResponse 생성 (정적 메서드)
             *
             * @param message 에러 메시지
             * @return ErrorResponse 객체
             */
            public static ErrorResponse of(String message) {
                return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message);
            }
        }
}