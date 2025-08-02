package com.spring.lukeapiserver.global.infra.google.service;

import com.spring.lukeapiserver.global.infra.google.config.GoogleProperties;
import com.spring.lukeapiserver.global.infra.google.dto.OAuth2Attribute;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class GoogleServiceImpl implements GoogleService {

    private final GoogleProperties googleProperties;
    private final RestTemplate restTemplate;

    public OAuth2Attribute getTokenInfo(String idToken) {
        OAuth2Attribute googleTokenInfo = restTemplate.getForObject(uriComponentGoogleTokenInfo(idToken), OAuth2Attribute.class);
        return googleTokenInfo;
    }

    private URI uriComponentGoogleTokenInfo(String idToken) {
        return UriComponentsBuilder.fromHttpUrl(googleProperties.getTokenInfo())
                .queryParam("id_token", idToken)
                .queryParam("Type", "json")
                .encode()
                .build().toUri();
    }

}
