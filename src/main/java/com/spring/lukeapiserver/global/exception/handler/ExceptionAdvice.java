package com.spring.lukeapiserver.global.exception.handler;
import com.spring.lukeapiserver.global.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

// 에러 처리 클래스
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    // 에러 응답 객체
    @Getter
    @Builder
    @RequiredArgsConstructor
    private static class ErrorResponse {
        private final int status;  // HTTP 상태 코드
        private final String message;  // 에러 메시지
    }

    /**
     * 사용자 정의 예외를 처리하는 메서드
     *
     * @param ex BusinessException 예외 객체
     * @return ResponseEntity 에러 응답과 상태 코드
     */
    @ExceptionHandler({BusinessException.class})  // 특정 예외를 처리하는 핸들러
    public ResponseEntity<ErrorResponse> handleException(BusinessException ex, HttpServletRequest httpServletRequest){
        // ErrorResponse 객체 생성
        ErrorResponse response = ErrorResponse.builder()
                .status(ex.getError().getStatus().value())  // 에러 객체에서 상태 코드 가져오기
                .message(ex.getError().getMessage())  // 에러 메시지 가져오기
                .build();
        // ResponseEntity 반환 (에러 응답 객체와 상태 코드 포함)
        return new ResponseEntity<ErrorResponse>(response, ex.getError().getStatus());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxSizeException(MaxUploadSizeExceededException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .status(ex.getStatusCode().value())
                .message("파일이 30MB 용량을 초과했습니다.")
                .build();
        return new ResponseEntity<ErrorResponse>(response, ex.getStatusCode());
    }

}
