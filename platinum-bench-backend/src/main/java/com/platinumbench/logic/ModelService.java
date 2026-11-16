package com.platinumbench.logic;

import java.util.List;
import java.util.Optional;

import com.platinumbench.persistence.*;
import com.platinumbench.domain.*;
import com.platinumbench.logic.exception.DuplicateModelException;
import com.platinumbench.logic.exception.ModelNotFoundException;

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

    public List<Model> getModelByName(String name){
        List<Model> listWithModelByName = modelRepository.getByName(name).stream().toList();
        return listWithModelByName;
    }

    public Model createModel(Model model){
        Optional<Model> verifiedModelByName = modelRepository.getByName(model.getName());

        if (verifiedModelByName.isPresent()){
            throw new DuplicateModelException(model.getName());
        }

        return modelRepository.create(model);
    }

    public Model updateModel(Long id, Model model){
        Optional<Model> modelById = modelRepository.getById(id);
        Optional<Model> modelByName = modelRepository.getByName(model.getName());
        Long modelByNameId = modelByName.map(Model :: getId).orElse(null);

        if (modelById.isEmpty()){
            throw new ModelNotFoundException(id);
        }

        if (modelByName.isPresent() && !modelByNameId.equals(id)){
            throw new DuplicateModelException(model.getName());
        }

        return modelRepository.update(model);
    }

    public void deleteModel(Long id){
        Optional<Model> ifModelExists = modelRepository.getById(id);
        
        if (ifModelExists.isEmpty()){
            throw new ModelNotFoundException(id);
        } 
            
        modelRepository.delete(id);
        
    }
} 
