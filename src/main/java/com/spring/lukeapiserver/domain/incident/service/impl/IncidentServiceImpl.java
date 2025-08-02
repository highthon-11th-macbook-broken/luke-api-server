package com.spring.lukeapiserver.domain.incident.service.impl;

import com.spring.lukeapiserver.domain.incident.client.dto.Incident;
import com.spring.lukeapiserver.domain.incident.client.dto.request.RegisterIncidentRequest;
import com.spring.lukeapiserver.domain.incident.domain.entity.IncidentEntity;
import com.spring.lukeapiserver.domain.incident.domain.repository.jpa.IncidentJpaRepository;
import com.spring.lukeapiserver.domain.incident.service.IncidentService;
import com.spring.lukeapiserver.domain.user.client.dto.User;
import com.spring.lukeapiserver.global.common.repository.UserSecurity;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncidentServiceImpl implements IncidentService {

    private final IncidentJpaRepository incidentJpaRepository;
    private final UserSecurity userSecurity;

    @Override
    public void registerIncident(RegisterIncidentRequest request) {
        User user = userSecurity.getUser();
        Random random = new Random();
        int number = random.nextInt(1_000_000); // 0 ~ 999999 범위
        incidentJpaRepository.save(IncidentEntity.builder()
                .incidentNumber(String.format("%06d", number))
                .email(user.email())
                .text(request.text())
                .riskLevel(request.riskLevel())
                .eventDateTime(request.eventDateTime())
                .source(request.source())
                .build());
    }

    @Override
    public List<Incident> getMyIncidents() {
        User user = userSecurity.getUser();
        return incidentJpaRepository.findAllByEmail(user.email()).stream().map(Incident::toIncident).toList();
    }

}
