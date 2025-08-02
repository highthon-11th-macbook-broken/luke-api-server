package com.spring.lukeapiserver.domain.user.client.dto.request;

import com.spring.lukeapiserver.domain.user.domain.enums.Gender;
import jakarta.validation.constraints.Pattern;

public record UserEditRequest(
        String name,
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "정해진 핸드폰 양식을 따라주세요 (010-0000-0000)")
        String phoneNumber,
        @Pattern(regexp = "^\\d{4}\\.\\d{2}\\.\\d{2}$", message = "생년월일은 YYYY.MM.DD 형식이어야 합니다.")
        String birthDate,
        Gender gender,
        String address,
        String pwPattern1,
        String pwPattern2,
        String pwPattern3
) {
}
