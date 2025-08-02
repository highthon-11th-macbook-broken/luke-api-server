package com.spring.lukeapiserver.domain.incident.domain.repository.jpa;

import com.spring.lukeapiserver.domain.incident.domain.entity.IncidentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentJpaRepository extends JpaRepository<IncidentEntity, Long> {
    List<IncidentEntity> findAllByEmail(String email);
    void deleteAllByEmail(String email);
}
