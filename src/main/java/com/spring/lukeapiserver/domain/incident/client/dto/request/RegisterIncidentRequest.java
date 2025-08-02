package com.spring.lukeapiserver.domain.incident.client.dto.request;

import com.spring.lukeapiserver.domain.incident.domain.enums.RiskLevel;

public record RegisterIncidentRequest(
        String text,
        RiskLevel riskLevel,
        String eventDateTime,
        String source
) {
}
