package com.spring.lukeapiserver.domain.auth.service.impl;

import com.spring.lukeapiserver.domain.auth.client.dto.request.GoogleSignInRequest;
import com.spring.lukeapiserver.domain.auth.client.dto.response.JsonWebTokenResponse;
import com.spring.lukeapiserver.domain.auth.client.dto.response.RefreshTokenResponse;
import com.spring.lukeapiserver.domain.auth.service.OAuthService;
import com.spring.lukeapiserver.domain.user.client.dto.User;
import com.spring.lukeapiserver.domain.user.domain.entity.UserEntity;
import com.spring.lukeapiserver.domain.user.domain.enums.UserRole;
import com.spring.lukeapiserver.domain.user.domain.repository.jpa.UserJpaRepository;
import com.spring.lukeapiserver.global.infra.google.dto.OAuth2Attribute;
import com.spring.lukeapiserver.global.infra.google.exception.AccountNotFoundException;
import com.spring.lukeapiserver.global.infra.google.service.GoogleService;
import com.spring.lukeapiserver.global.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {

    private final GoogleService googleService;
    private final JwtProvider jwtProvider;
    private final UserJpaRepository userJpaRepository;

    @Override
    public JsonWebTokenResponse googleSignIn(GoogleSignInRequest request) {
        OAuth2Attribute oAuth2Attribute = googleService.getTokenInfo(request.idToken());
        // 구글 계정 존재 여부 확인
        if (!Strings.hasText(oAuth2Attribute.email())) {
            // 없을 경우 AccountNotFoundException 발생
            throw AccountNotFoundException.EXCEPTION;
        }
        // 구글 이메일로 유저 정보 추출
        User user = findOrSave(oAuth2Attribute);
        return JsonWebTokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.email(), user.userRole()))
                .refreshToken(jwtProvider.generateRefreshToken(user.email(), user.userRole()))
                .build();
    }

    @Override
    public RefreshTokenResponse refresh(String token) {
        // JWT 토큰의 claims 정보 추출
        Jws<Claims> claims = jwtProvider.getClaims(token);

        // 새 엑세스 토큰 발급
        return RefreshTokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(claims.getBody().getSubject(),
                        (UserRole) claims.getHeader().get("authority"))).build();
    }

    private User findOrSave(OAuth2Attribute oAuth2Attribute) {
        User user = userJpaRepository.findByEmail(oAuth2Attribute.email()).map(User::toUser).orElse(null);
        if (user == null) {
            return save(User.createEntity(oAuth2Attribute));
        }
        return user;
    }

    private User save(final UserEntity userEntity) {
        return User.toUser(userJpaRepository.save(userEntity));
    }


}
