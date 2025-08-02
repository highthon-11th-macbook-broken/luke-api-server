package com.spring.lukeapiserver.domain.user.domain.entity;

import com.spring.lukeapiserver.domain.user.domain.enums.Gender;
import com.spring.lukeapiserver.domain.user.domain.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@Table(name = "tb_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @Column(nullable = false, unique = true)
    private String email;

    private String name;

    private String phoneNumber;

    private String birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String ip;

    private String address;

    private String pwPattern1;

    private String pwPattern2;

    private String pwPattern3;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
