package com.spring.lukeapiserver.global.security.auth;

import com.spring.lukeapiserver.domain.user.client.dto.User;
import java.util.Collection;
import java.util.Collections;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// 사용자 정의 UserDetails 구현 클래스
// Spring Security에서 사용자 인증 및 권한 관리를 위해 사용됨
@Getter
public class CustomUserDetails implements UserDetails {

    // 인증 대상 사용자 정보를 저장하는 객체
    private final User user;

    // 사용자 권한 정보
    private Collection<? extends GrantedAuthority> authorities;

    // 생성자: 사용자와 권한 정보를 함께 초기화
    private CustomUserDetails(final User user, final Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    // 생성자: 사용자 정보만 초기화
    public CustomUserDetails(final User userDTO) {
        this.user = userDTO;
    }

    /**
     * 정적 팩토리 메서드
     * User 객체로부터 CustomUserDetails 객체를 생성
     *
     * @param user 사용자 정보
     * @return CustomUserDetails 객체
     */
    public static CustomUserDetails create(User user) {
        return new CustomUserDetails(
                user,
                Collections.singleton((GrantedAuthority) user.userRole()::getKey) // 사용자 역할을 권한으로 변환
        );
    }

    /**
     * 사용자 권한 정보를 반환
     *
     * @return GrantedAuthority 컬렉션
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // 권한 정보를 반환
    }

    /**
     * 사용자 비밀번호 반환 (현재는 null 처리)
     *
     * @return 비밀번호 (null)
     */
    @Override
    public String getPassword() {
        return null; // 비밀번호는 반환하지 않음
    }

    /**
     * 사용자 이름 (이메일) 반환
     *
     * @return 사용자 이메일
     */
    @Override
    public String getUsername() {
        return user.email();
    }

    /**
     * 계정 만료 여부 확인
     *
     * @return 만료되지 않았으면 true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 상태를 항상 활성으로 처리
    }

    /**
     * 계정 잠금 여부 확인
     *
     * @return 잠금되지 않았으면 true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠금 상태를 항상 활성으로 처리
    }

    /**
     * 인증 정보 만료 여부 확인
     *
     * @return 만료되지 않았으면 true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 인증 정보 만료 상태를 항상 활성으로 처리
    }

    /**
     * 계정 활성화 여부 확인
     *
     * @return 활성화 상태면 true
     */
    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화 상태를 항상 활성으로 처리
    }
}
