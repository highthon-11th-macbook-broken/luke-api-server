package com.spring.lukeapiserver.domain.user.service;

import com.spring.lukeapiserver.domain.user.client.dto.User;
import com.spring.lukeapiserver.domain.user.client.dto.request.UserEditRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    User getUser();

    void editUser(UserEditRequest request, HttpServletRequest httpRequest);

    void deleteUser();
}
