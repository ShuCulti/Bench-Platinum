package com.platinumbench.controller;

import java.util.List;
import java.util.Optional;
import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platinumbench.controller.dto.CreateModelRequestDto;
import com.platinumbench.domain.Model;
import com.platinumbench.logic.ModelService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/models") 
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<List<Model>> getAllModels(@RequestParam(required = false) String name){
        if (name != null){
            return ResponseEntity.ok(modelService.getModelByName(name));
        }
        return ResponseEntity.ok(modelService.getAllModels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Model>> getModelById(@PathVariable Long id){
        Optional<Model> modelById = modelService.getModelById(id);

        if (modelById.isPresent()){
            return ResponseEntity.ok(modelById); 
        }
        
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Model> createModel(@Valid @RequestBody CreateModelRequestDto model){
        Model modelTbc = new Model(
            null,
            model.name(),
            model.family(),
            model.parameterB()
        );
        Model created = modelService.createModel(modelTbc);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();


        return ResponseEntity.created(location).body(created);
    }
    
    @PutMapping ("/{id}")
    public ResponseEntity<Model> updateModel (@PathVariable Long id, @Valid @RequestBody CreateModelRequestDto model) {
        Model modelTbu = new Model(
            id,
            model.name(), 
            model.family(),
            model.parameterB()
        );

        Model updated = modelService.updateModel(id, modelTbu);

        return ResponseEntity.ok(updated);
    }
    

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long id){
        modelService.deleteModel(id);
        
        return ResponseEntity.noContent().build();
    }

}
