package com.spring.lukeapiserver.domain.user.client.api;

import com.spring.lukeapiserver.domain.user.client.dto.User;
import com.spring.lukeapiserver.domain.user.client.dto.request.UserEditRequest;
import com.spring.lukeapiserver.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "USER API", description = "USER API")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "유저 단일 조회")
    public User getUser(){
        return userService.getUser();
    }

    @PutMapping
    @Operation(summary = "유저 정보 수정")
    public void editUser(@RequestBody @Valid UserEditRequest request, HttpServletRequest httpRequest) {
        userService.editUser(request, httpRequest);
    }

    @DeleteMapping
    @Operation(summary = "유저 회원탈퇴")
    public void deleteUser() {
        userService.deleteUser();
    }

}
