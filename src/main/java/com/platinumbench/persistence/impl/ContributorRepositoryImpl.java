package com.platinumbench.persistence.impl;

import com.platinumbench.domain.Contributor;
import com.platinumbench.persistence.ContributorRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContributorRepositoryImpl implements ContributorRepository {

    private final List<Contributor> contributors = new ArrayList<>();
    private long nextId = 1;

    @Override
    public List<Contributor> getAll() {
        return contributors;
    }

    @Override
    public Contributor getContributorById(Long id) {
        for (Contributor contributor : contributors) {
            if (contributor.getId().equals(id)) {
                return contributor;
            }
        }
        return null;
    }

    @Override
    public Contributor create(Contributor contributor) {
        contributor.setId(nextId);
        nextId++;
        contributors.add(contributor);
        return contributor;
    }

    @Override
    public void delete(Long id) {
        for (int i = 0; i < contributors.size(); i++) {
            if (contributors.get(i).getId().equals(id)) {
                contributors.remove(i);
                return;
            }
        }
    }
}
