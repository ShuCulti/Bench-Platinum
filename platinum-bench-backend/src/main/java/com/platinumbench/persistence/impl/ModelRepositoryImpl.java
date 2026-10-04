package com.platinumbench.persistence.impl;

import com.platinumbench.persistence.ModelRepository;
import com.platinumbench.domain.Model;
import com.platinumbench.persistence.ModelJpaRepository;
import com.platinumbench.persistence.entity.ModelEntity;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository

public class ModelRepositoryImpl implements ModelRepository{

    private final ModelJpaRepository jpa;

    public ModelRepositoryImpl(ModelJpaRepository jpa){

        this.jpa = jpa;
    }

    @Override
    public List<Model> getAll(){
        return jpa.findAll().stream().map(this::toDomain).toList();

    }

    @Override
    public Optional<Model> getById(Long id){
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<Model> getByName(String name){
        return jpa.findByName(name).map(this::toDomain);
    }

    @Override
    public Model create(Model model){
        ModelEntity saved = jpa.save(toEntity(model));
        return toDomain(saved);
    }

    private Model toDomain(ModelEntity entity){
        return new Model(
                entity.getId(),
                entity.getName(),
                entity.getFamily(),
                ((int) entity.getParameterB())
        );
    }

    private ModelEntity toEntity(Model model){
        ModelEntity entity = new ModelEntity();
        entity.setName(model.getName());
        entity.setFamily(model.getFamily());
        entity.setParameterB(model.getParameterB());

        return entity;

    }

}