package com.spring.lukeapiserver.domain.auth.client.dto.request;

import jakarta.validation.constraints.NotBlank;

// 소셜 로그인 요청 객체
public record GoogleSignInRequest(

        // 구글 로그인시 필요한 인증 토큰
        @NotBlank
        String idToken


){}
