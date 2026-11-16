package com.platinumbench.controller;

import java.lang.annotation.Repeatable;
import java.lang.module.ResolutionException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.platinumbench.controller.dto.CreateEngineRequestDto;
import com.platinumbench.domain.Engine;
import com.platinumbench.logic.EngineService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RequestMapping("/api/engines")
@RestController 

public class EngineController {
    private EngineService engineService;

    public EngineController(EngineService engineService) {
        this.engineService = engineService;
        
    }

    @GetMapping
    public ResponseEntity<List<Engine>> getAllEngines(){
        List<Engine> listOfEngines = engineService.getAllEngines();
        return ResponseEntity.ok(listOfEngines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Engine>> getEngineById(@PathVariable Long id){
        Optional<Engine> engineById = engineService.getEngineById(id);

        if (engineById.isPresent()){
            return ResponseEntity.ok(engineById);
        }
        
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Engine> createEngine(@Valid @RequestBody CreateEngineRequestDto engine) {
        Engine engineTbc = new Engine(null, engine.name(), engine.version(), engine.buildCommit());
        Engine created = engineService.createEngine(engineTbc);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Engine> update(@Valid @PathVariable Long id, @RequestBody CreateEngineRequestDto engine) {
        Engine engineTbu = new Engine(id, engine.name(), engine.version(), engine.buildCommit());
        Engine updated = engineService.createEngine(engineTbu);
        
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEngine(@PathVariable Long id){
        engineService.deleteEngine(id);

        return ResponseEntity.noContent().build();
    }
    

}
