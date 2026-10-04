package com.platinumbench.logic.impl;

import com.platinumbench.domain.Model;
import com.platinumbench.persistence.entity.ModelEntity;

public class ModelConverter{

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