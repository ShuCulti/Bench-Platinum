package com.platinumbench.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platinumbench.domain.Model;
import com.platinumbench.logic.ModelService;


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

    @PostMapping("/create")
    public ResponseEntity<Model> createModel(Model model){
        Model created = modelService.createModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
