package com.platinumbench.persistence;

import com.platinumbench.persistence.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelJpaRepository extends JpaRepository<ModelEntity,Long>{
    public Optional<ModelEntity> findByName(String name);

}