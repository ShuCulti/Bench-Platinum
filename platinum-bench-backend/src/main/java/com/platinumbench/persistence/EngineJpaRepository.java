package com.platinumbench.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platinumbench.persistence.entity.EngineEntity;

public interface EngineJpaRepository extends JpaRepository<EngineEntity, Long> {
    public Optional<EngineEntity> findByNameAndVersion(String name, String version);
}
