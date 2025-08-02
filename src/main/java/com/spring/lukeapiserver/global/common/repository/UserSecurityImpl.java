package com.spring.lukeapiserver.global.common.repository;

import com.spring.lukeapiserver.domain.user.client.dto.User;
import com.spring.lukeapiserver.global.security.auth.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

// 유저 정보 인터페이스 구현체
@Repository
public class UserSecurityImpl implements UserSecurity {

    // 현재 로그인 한 유저 객체를 가져옴
    @Override
    public User getUser() {
        // SecurityContextHolder에서 인증 정보를 가져와서 현재 로그인한 유저 정보를 반환
        return ((CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }

}
