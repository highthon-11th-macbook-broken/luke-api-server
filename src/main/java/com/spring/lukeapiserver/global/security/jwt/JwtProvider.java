package com.spring.lukeapiserver.global.security.jwt;

import com.spring.lukeapiserver.domain.user.domain.enums.UserRole;
import com.spring.lukeapiserver.global.security.jwt.config.JwtProperties;
import com.spring.lukeapiserver.global.security.jwt.enums.JwtType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// JWT 관련 기능 제공 클래스
// JWT 토큰 생성 및 클레임 검증 등을 담당
@Component
@RequiredArgsConstructor
public class JwtProvider {

    // JWT 설정 정보 (시크릿 키 등)를 담은 객체
    private final JwtProperties jwtProperties;

    /**
     * JWT 토큰에서 클레임을 추출
     *
     * @param token JWT 토큰
     * @return 추출된 JWS(서명된 클레임)
     * @throws IllegalArgumentException 만료된, 지원되지 않는, 잘못된 토큰인 경우 발생
     */
    public Jws<Claims> getClaims(final String token) {
        try {
            // JWT 파서를 이용해 토큰의 클레임을 파싱
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) // 시크릿 키 설정
                    .parseClaimsJws(token); // 토큰 검증 및 파싱
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("만료된 토큰");
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("지원되지 않는 토큰");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 토큰");
        }
    }

    public String generateAccessToken(final String id, final UserRole userRole) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS) // 헤더에 JWT 타입 설정
                .setSubject(id) // subject에 사용자 아이디 설정
                .claim("authority", userRole) // 클레임에 사용자 권한 추가
                .setIssuedAt(new Date(System.currentTimeMillis())) // 토큰 생성 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration())) // 만료 시간 설정 ( 24시간 )
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) // 서명 알고리즘과 키 설정
                .compact(); // 토큰 생성
    }

    public String generateRefreshToken(final String id, final UserRole userRole) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.REFRESH) // 헤더에 JWT 타입 설정
                .setSubject(id) // subject에 사용자 아이디 설정
                .claim("authority", userRole) // 클레임에 사용자 권한 추가
                .setIssuedAt(new Date(System.currentTimeMillis())) // 토큰 생성 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExpiration())) // 만료 시간 설정 ( 7일 )
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey()) // 서명 알고리즘과 키 설정
                .compact(); // 토큰 생성
    }
}