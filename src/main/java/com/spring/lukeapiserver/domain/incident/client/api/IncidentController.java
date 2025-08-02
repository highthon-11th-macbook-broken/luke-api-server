package com.spring.lukeapiserver.domain.incident.client.api;

import com.spring.lukeapiserver.domain.incident.client.dto.Incident;
import com.spring.lukeapiserver.domain.incident.client.dto.request.RegisterIncidentRequest;
import com.spring.lukeapiserver.domain.incident.service.IncidentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/incident")
@RequiredArgsConstructor
@Tag(name = "사건 API", description = "사건 API")
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping
    @Operation(summary = "프롬포팅된 사건 스토리를 저장합니다")
    private void registerIncident(RegisterIncidentRequest request) {
        incidentService.registerIncident(request);
    }

    @GetMapping
    @Operation(summary = "나의 사건 목록을 가져옵니다.")
    public List<Incident> getMyIncidents() {
        return incidentService.getMyIncidents();
    }

}
