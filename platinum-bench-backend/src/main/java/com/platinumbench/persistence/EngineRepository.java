package com.platinumbench.persistence;

import java.util.List;
import java.util.Optional;

import com.platinumbench.domain.Engine;

public interface EngineRepository {
    List<Engine> getAll();
    Optional<Engine> getById(Long id);
    Optional<Engine> getByNameAndVersion(String name, String version);
    Engine create(Engine engine);
    Engine update(Engine engine);
    void delete(Long id);
}
