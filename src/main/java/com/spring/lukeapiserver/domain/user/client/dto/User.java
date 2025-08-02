package com.spring.lukeapiserver.domain.user.client.dto;

import com.spring.lukeapiserver.domain.user.domain.entity.UserEntity;
import com.spring.lukeapiserver.domain.user.domain.enums.Gender;
import com.spring.lukeapiserver.domain.user.domain.enums.UserRole;

public record User(
        String email,
        String name,
        String phoneNumber,
        String birthDate,
        Gender gender,
        String ip,
        String address,
        String pwPattern1,
        String pwPattern2,
        String pwPattern3,
        UserRole userRole
) {
    public static User toUser(UserEntity entity) {
        return new User(
                entity.getEmail(),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getBirthDate(),
                entity.getGender(),
                entity.getIp(),
                entity.getAddress(),
                entity.getPwPattern1(),
                entity.getPwPattern2(),
                entity.getPwPattern3(),
                entity.getUserRole()
                );
    }
}
