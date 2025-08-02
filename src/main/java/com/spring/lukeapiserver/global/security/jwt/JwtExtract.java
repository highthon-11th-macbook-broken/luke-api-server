package com.spring.lukeapiserver.global.security.jwt;

import com.spring.lukeapiserver.domain.user.client.dto.User;
import com.spring.lukeapiserver.domain.user.domain.repository.jpa.UserJpaRepository;
import com.spring.lukeapiserver.global.exception.NotFoundException;
import com.spring.lukeapiserver.global.security.auth.CustomUserDetails;
import com.spring.lukeapiserver.global.security.jwt.enums.JwtType;
import com.spring.lukeapiserver.global.security.jwt.exception.TokenTypeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

// JWT 관련 유틸리티 클래스
// JWT 토큰의 추출, 검증, 인증 객체 생성을 담당
@Component
@RequiredArgsConstructor
public class JwtExtract {

    // 사용자 정보를 조회하기 위한 JPA 레포지토리
    private final UserJpaRepository userRepository;

    // JWT 토큰의 발급 및 검증을 담당하는 클래스
    private final JwtProvider jwtProvider;

    public Authentication getAuthentication(final String token) {
        // JWT 토큰에서 클레임(Claims) 추출
        final Jws<Claims> claims = jwtProvider.getClaims(token);

        // 토큰 타입이 올바르지 않으면 예외 발생
        if (isWrongType(claims, JwtType.ACCESS)) {
            throw TokenTypeException.EXCEPTION;
        }

        // 클레임에서 사용자 아이디를 기반으로 사용자 조회
        User user = userRepository
                .findById(claims.getBody().getSubject()) // subject에 저장된 아이디로 사용자 검색
                .map(User::toUser) // user 모델 객체로 변환
                .orElseThrow(() -> NotFoundException.EXCEPTION); // 사용자 없을 시 예외 발생

        // 사용자 정보를 기반으로 CustomUserDetails 생성
        final CustomUserDetails details = new CustomUserDetails(user);

        // 인증 객체 반환 (권한 정보 포함)
        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        // Authorization 헤더에서 토큰 추출
        return extractToken(request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    public String extractToken(final String token) {
        // 토큰이 비어 있지 않고 "Bearer "로 시작하는 경우 접두어 제거
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token; // 유효하지 않은 경우 그대로 반환
    }

    public boolean isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
        // 클레임 헤더의 JWT 타입이 기대하는 타입과 일치하지 않으면 true 반환
        return !(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()));
    }

}
