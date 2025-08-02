package com.spring.lukeapiserver.domain.auth.service;

import com.spring.lukeapiserver.domain.auth.client.dto.request.GoogleSignInRequest;
import com.spring.lukeapiserver.domain.auth.client.dto.response.JsonWebTokenResponse;
import com.spring.lukeapiserver.domain.auth.client.dto.response.RefreshTokenResponse;

public interface OAuthService {
    JsonWebTokenResponse googleSignIn(GoogleSignInRequest request);

    RefreshTokenResponse refresh(String token);
}
