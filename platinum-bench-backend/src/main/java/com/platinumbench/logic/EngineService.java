package com.platinumbench.logic;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.platinumbench.domain.Engine;
import com.platinumbench.logic.exception.DuplicateEngineException;
import com.platinumbench.logic.exception.EngineNotFoundException;
import com.platinumbench.persistence.EngineRepository;

@Service 
public class EngineService {
    EngineRepository engineRepository;

    public EngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    public List<Engine> getAllEngines(){
        return engineRepository.getAll();
    }

    public Optional<Engine> getEngineById(Long id){
        return engineRepository.getById(id);
    }

    public Optional<Engine> getEngineByNameAndVersion(String name, String version){
        return engineRepository.getByNameAndVersion(name, version);
    }

    public Engine createEngine(Engine engine){
        Optional<Engine> engineExists = engineRepository.getByNameAndVersion(engine.getName(), engine.getVersion());
        

        if (engineExists.isPresent()){
            throw new DuplicateEngineException(engine.getName(), engine.getVersion());
        }

        return engineRepository.create(engine);
    }

    public Engine updateEngine(Long id, Engine engine){
        Optional<Engine> engineByIdExists = engineRepository.getById(id);
        Optional<Engine> engineByNameExists = engineRepository.getByNameAndVersion(engine.getName(), engine.getVersion());
        Long engineByNameId = engineByNameExists.map(Engine :: getId).orElse(null);

        if (engineByIdExists.isEmpty()){
            throw new EngineNotFoundException(id);
        }

        if (engineByNameExists.isPresent() && !engineByNameId.equals(id)){
            throw new DuplicateEngineException(engine.getName(), engine.getVersion());
        }

        return engineRepository.update(engine);
    }

    public void deleteEngine (Long id){
        engineRepository.delete(id);
    }
}
