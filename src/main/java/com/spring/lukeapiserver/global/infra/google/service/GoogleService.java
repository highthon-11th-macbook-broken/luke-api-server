package com.spring.lukeapiserver.global.infra.google.service;


import com.spring.lukeapiserver.global.infra.google.dto.OAuth2Attribute;

public interface GoogleService {

    OAuth2Attribute getTokenInfo(String idToken);

}
