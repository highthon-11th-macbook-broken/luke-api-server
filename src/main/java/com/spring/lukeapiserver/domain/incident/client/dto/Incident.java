package com.spring.lukeapiserver.domain.incident.client.dto;

import com.spring.lukeapiserver.domain.incident.domain.entity.IncidentEntity;

public record Incident(
        Long idx,
        String email,
        String text
) {
    public static Incident toIncident(IncidentEntity entity) {
        return new Incident(
                entity.getIdx(),
                entity.getEmail(),
                entity.getText()
        );
    }
}
