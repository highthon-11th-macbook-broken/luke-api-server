package com.spring.lukeapiserver.domain.user.domain.repository.jpa;

import com.spring.lukeapiserver.domain.user.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
}
