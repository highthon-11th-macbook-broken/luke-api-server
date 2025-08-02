package com.spring.lukeapiserver.domain.incident.client.dto;

import com.spring.lukeapiserver.domain.incident.domain.entity.IncidentEntity;
import java.time.LocalDateTime;

public record Incident(
        Long idx,
        String incidentNumber,
        String email,
        String text,
        LocalDateTime createdAt
) {
    public static Incident toIncident(IncidentEntity entity) {
        return new Incident(
                entity.getIdx(),
                entity.getIncidentNumber(),
                entity.getEmail(),
                entity.getText(),
                entity.getCreatedAt()
        );
    }
}
