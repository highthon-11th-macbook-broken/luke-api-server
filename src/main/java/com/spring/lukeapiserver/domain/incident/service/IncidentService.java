package com.spring.lukeapiserver.domain.incident.service;

import com.spring.lukeapiserver.domain.incident.client.dto.Incident;
import com.spring.lukeapiserver.domain.incident.client.dto.request.RegisterIncidentRequest;
import java.util.List;

public interface IncidentService {
    void registerIncident(RegisterIncidentRequest request);

    List<Incident> getMyIncidents();
}
