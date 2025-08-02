package com.spring.lukeapiserver.global.common.repository;


import com.spring.lukeapiserver.domain.user.client.dto.User;

// 유저 정보 메서드 정의 해놓은 인터페이스
public interface UserSecurity {

    // 현재 로그인한 유저 정보를 반환하는 메서드
    User getUser();

}
