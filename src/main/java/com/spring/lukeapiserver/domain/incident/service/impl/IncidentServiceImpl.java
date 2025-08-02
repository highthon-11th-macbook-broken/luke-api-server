package com.spring.lukeapiserver.domain.incident.service.impl;

import com.spring.lukeapiserver.domain.incident.client.dto.Incident;
import com.spring.lukeapiserver.domain.incident.client.dto.request.RegisterIncidentRequest;
import com.spring.lukeapiserver.domain.incident.domain.entity.IncidentEntity;
import com.spring.lukeapiserver.domain.incident.domain.repository.jpa.IncidentJpaRepository;
import com.spring.lukeapiserver.domain.incident.service.IncidentService;
import com.spring.lukeapiserver.domain.user.client.dto.User;
import com.spring.lukeapiserver.global.common.repository.UserSecurity;
import java.util.List;
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
        incidentJpaRepository.save(IncidentEntity.builder()
                .email(user.email())
                .text(request.text())
                .build());
    }

    @Override
    public List<Incident> getMyIncidents() {
        User user = userSecurity.getUser();
        return incidentJpaRepository.findAllByEmail(user.email()).stream().map(Incident::toIncident).toList();
    }

}
