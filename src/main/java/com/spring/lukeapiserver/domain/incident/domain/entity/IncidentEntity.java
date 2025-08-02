package com.spring.lukeapiserver.domain.incident.domain.entity;

import com.spring.lukeapiserver.domain.incident.domain.enums.RiskLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@SuperBuilder
@Table(name = "tb_incident")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IncidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String incidentNumber;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;

    private String eventDateTime;

    private String source;

    @CreatedDate
    private LocalDateTime createdAt;

}
