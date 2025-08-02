package com.spring.lukeapiserver.global.security.jwt.filter;

import com.spring.lukeapiserver.global.security.jwt.JwtExtract;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// JWT 인증 필터 클래스
// Spring Security의 OncePerRequestFilter를 상속받아 요청마다 JWT 인증을 처리하는 필터를 구현
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // JWT 토큰 추출 및 인증 객체 생성을 위한 의존성 주입
    private final JwtExtract jwtExtract;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, ExpiredJwtException {

        // 요청 헤더에서 JWT 토큰을 추출
        String token = jwtExtract.extractTokenFromRequest(request);

        // 추출한 토큰이 null이 아니면 인증 객체를 SecurityContextHolder에 설정
        if (token != null) {
            // JWT 토큰을 기반으로 Authentication 객체 생성 및 설정
            SecurityContextHolder.getContext().setAuthentication(jwtExtract.getAuthentication(token));
        }

        // 필터 체인의 다음 단계로 요청과 응답 전달
        filterChain.doFilter(request, response);
    }
}