package com.platinumbench.logic;

import java.util.List;
import java.util.Optional;

import com.platinumbench.persistence.*;
import com.platinumbench.domain.*;

import org.springframework.stereotype.Service;

@Service 
public class ModelService{

    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<Model> getAllModels(){
        return modelRepository.getAll();
    }

    public Optional<Model> getModelById(Long id){
        return modelRepository.getById(id);
    }

    public Optional<Model> getModelByName(String name){
        return modelRepository.getByName(name);
    }

    public Model createModel(Model model){
        return modelRepository.create(model);
    }
} 
