package com.spring.lukeapiserver.domain.user.service.impl;

import com.spring.lukeapiserver.domain.user.client.dto.User;
import com.spring.lukeapiserver.domain.user.client.dto.request.UserEditRequest;
import com.spring.lukeapiserver.domain.user.domain.entity.UserEntity;
import com.spring.lukeapiserver.domain.user.domain.repository.jpa.UserJpaRepository;
import com.spring.lukeapiserver.domain.user.service.UserService;
import com.spring.lukeapiserver.global.common.repository.UserSecurity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final UserSecurity userSecurity;

    @Override
    public User getUser(){
        return userSecurity.getUser();
    }

    @Override
    public void editUser(UserEditRequest request, HttpServletRequest httpRequest) {
        User user = getUser();
        userJpaRepository.save(UserEntity.builder()
                        .email(user.email())
                        .name(request.name())
                        .phoneNumber(request.phoneNumber())
                        .birthDate(request.birthDate())
                        .gender(request.gender())
                        .ip(httpRequest.getRemoteAddr())
                        .address(request.address())
                        .pwPattern1(request.pwPattern1())
                        .pwPattern2(request.pwPattern2())
                        .pwPattern3(request.pwPattern3())
                        .userRole(user.userRole())
                .build());
    }

    @Override
    public void deleteUser() {
        User user = getUser();
        userJpaRepository.deleteById(user.email());
    }

}
