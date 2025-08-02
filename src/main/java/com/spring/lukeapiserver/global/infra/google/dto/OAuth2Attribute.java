package com.spring.lukeapiserver.global.infra.google.dto;

public record OAuth2Attribute (
        String email,
        String email_verified,
        String name
){}
