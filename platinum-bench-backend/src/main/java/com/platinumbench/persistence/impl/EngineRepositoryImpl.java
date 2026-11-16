package com.platinumbench.persistence.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.platinumbench.domain.Engine;
import com.platinumbench.persistence.EngineJpaRepository;
import com.platinumbench.persistence.EngineRepository;
import com.platinumbench.persistence.entity.EngineEntity;

@Repository 

public class EngineRepositoryImpl implements EngineRepository{

    private EngineJpaRepository jpa;

    public EngineRepositoryImpl(EngineJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override 
    public List<Engine> getAll(){
        return jpa.findAll().stream().map(this :: toDomain).toList();
    };

    @Override
    public Optional<Engine> getById(Long id){
        return jpa.findById(id).map(this::toDomain);
    };

    @Override
    public Optional<Engine> getByNameAndVersion(String name, String version){
        return jpa.findByNameAndVersion(name, version).map(this::toDomain);
    };

    @Override
    public Engine create(Engine engine){
        EngineEntity saved = jpa.save(toEntity(engine));
        return toDomain(saved);
    };

    @Override
    public Engine update(Engine engine){
        EngineEntity updated = jpa.save(toEntity(engine));
        return toDomain(updated);
    };

    @Override
    public void delete(Long id){
        jpa.deleteById(id);
    };

    public Engine toDomain(EngineEntity entity){
        return new Engine(
            entity.getId(),
            entity.getName(),
            entity.getVersion(),
            entity.getBuildCommit()
        );
    }

    public EngineEntity toEntity(Engine engine){
        EngineEntity entity = new EngineEntity(
        engine.getId(), 
        engine.getName(), 
        engine.getVersion(), 
        engine.getBuildCommit());

        return entity;
    }

}
