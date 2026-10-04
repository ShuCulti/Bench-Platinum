package com.platinumbench.controller;

import com.platinumbench.logic.ContributorService;
import com.platinumbench.domain.Contributor;
import com.platinumbench.domain.CreateContributorRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contributors")
public class ContributorController {

    private final ContributorService contributorService;

    public ContributorController(ContributorService contributorService) {
        this.contributorService = contributorService;
    }

    @GetMapping
    public ResponseEntity<List<Contributor>> getAll() {
        return ResponseEntity.ok(contributorService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contributor> getById(@PathVariable Long id) {
        Contributor contributor = contributorService.getById(id);
        if (contributor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contributor);
    }

    @PostMapping
    public ResponseEntity<Contributor> create(@RequestBody CreateContributorRequest request) {
        Contributor created = contributorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
