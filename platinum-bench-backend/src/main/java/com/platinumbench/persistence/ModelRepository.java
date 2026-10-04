package com.platinumbench.persistence;
import com.platinumbench.domain.Model;

import java.util.List;
import java.util.Optional;

public interface ModelRepository {
    List<Model>  getAll();
    Optional<Model> getById(Long id);
    Optional<Model> getByName(String name);
    Model create(Model model);

}