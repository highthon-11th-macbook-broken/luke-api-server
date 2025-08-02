package com.spring.lukeapiserver.domain.user.client.dto.request;

import com.spring.lukeapiserver.domain.user.domain.enums.Gender;

public record UserEditRequest(
        String name,
        String phoneNumber,
        String birthDate,
        Gender gender,
        String address,
        String pwPattern1,
        String pwPattern2,
        String pwPattern3
) {
}
