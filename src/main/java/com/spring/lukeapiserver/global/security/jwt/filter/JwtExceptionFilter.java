package com.spring.lukeapiserver.global.security.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// JWT 예외 처리 필터 클래스
// Spring Security의 OncePerRequestFilter를 상속받아 요청 처리 중 발생한 예외를 처리
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException {
        try {
            // 요청을 필터 체인의 다음 단계로 전달
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            // IllegalArgumentException 발생 시 401 UNAUTHORIZED 응답 설정
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, e);
        } catch (ServletException e) {
            // ServletException 발생 시 400 BAD_REQUEST 응답 설정
            setErrorResponse(HttpStatus.BAD_REQUEST, response, e);
        }
    }

    public void setErrorResponse(HttpStatus status,
                                 HttpServletResponse response,
                                 Throwable ex) throws IOException {
        // 응답 Content-Type을 JSON 형식으로 설정
        response.setContentType("application/json;charset=UTF-8");
        // 응답 상태 코드를 설정
        response.setStatus(status.value());

        // ObjectMapper를 사용하여 예외 정보를 JSON으로 변환
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        map.put("status", String.valueOf(status.value())); // 상태 코드
        map.put("message", ex.getMessage()); // 예외 메시지

        // 변환된 JSON 데이터를 응답 본문에 작성
        response.getWriter().write(mapper.writeValueAsString(map));
    }

}