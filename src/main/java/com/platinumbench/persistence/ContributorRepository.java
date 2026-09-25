package com.platinumbench.persistence;

import java.util.List;

import com.platinumbench.domain.Contributor;

public interface ContributorRepository{
    List<Contributor> getAll();
    Contributor getContributorById(Long id);
    Contributor create(Contributor contributor);
    void delete(Long id); //might add/remove ts idrk
}