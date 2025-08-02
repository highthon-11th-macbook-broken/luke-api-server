package com.spring.lukeapiserver.domain.auth.client.api;

import com.spring.lukeapiserver.domain.auth.client.dto.request.GoogleSignInRequest;
import com.spring.lukeapiserver.domain.auth.client.dto.request.RefreshTokenRequest;
import com.spring.lukeapiserver.domain.auth.client.dto.response.JsonWebTokenResponse;
import com.spring.lukeapiserver.domain.auth.client.dto.response.RefreshTokenResponse;
import com.spring.lukeapiserver.domain.auth.service.OAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "AUTH API", description = "AUTH API")
public class AuthController {

    private final OAuthService oAuthService;

    @PostMapping("/sign-in")
    @Operation(summary = "구글 로그인")
    public JsonWebTokenResponse googleSignIn(@RequestBody @Valid GoogleSignInRequest request) {
        return oAuthService.googleSignIn(request);
    }

    @PostMapping("/refresh")
    @Operation(summary = "토큰 재발급")
    public RefreshTokenResponse refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return oAuthService.refresh(request.refreshToken());
    }

}
