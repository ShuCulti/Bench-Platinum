package com.platinumbench.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.platinumbench.domain.*;

import java.util.List;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platinumbench.logic.ModelService;

@RestController
@RequestMapping("/api/models") 
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<List<Model>> getAllModels(){
        return ResponseEntity.ok(modelService.getAllModels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Model>> getModelById(Long id){
        return ResponseEntity.ok(modelService.getModelById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Optional<Model>> getModelByName(String name){
        return ResponseEntity.ok(modelService.getModelByName(name));
    }

    @PostMapping("/create")
    public ResponseEntity<Model> createModel(Model model){
        Model created = modelService.createModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
