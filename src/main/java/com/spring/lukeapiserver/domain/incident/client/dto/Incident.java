package com.spring.lukeapiserver.domain.incident.client.dto;

import com.spring.lukeapiserver.domain.incident.domain.entity.IncidentEntity;
import com.spring.lukeapiserver.domain.incident.domain.enums.RiskLevel;
import java.time.LocalDateTime;

public record Incident(
        Long idx,
        String incidentNumber,
        String email,
        String text,
        RiskLevel riskLevel,
        String eventDateTime,
        String source,
        LocalDateTime createdAt
) {
    public static Incident toIncident(IncidentEntity entity) {
        return new Incident(
                entity.getIdx(),
                entity.getIncidentNumber(),
                entity.getEmail(),
                entity.getText(),
                entity.getRiskLevel(),
                entity.getEventDateTime(),
                entity.getSource(),
                entity.getCreatedAt()
        );
    }
}
