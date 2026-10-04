package com.platinumbench.logic;

import com.platinumbench.domain.Contributor;
import com.platinumbench.domain.CreateContributorRequest;
import com.platinumbench.persistence.ContributorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContributorService {

    private final ContributorRepository contributorRepository;

    public ContributorService(ContributorRepository contributorRepository) {
        this.contributorRepository = contributorRepository;
    }

    public List<Contributor> getAll() {
        return contributorRepository.getAll();
    }

    public Contributor getById(Long id) {
        return contributorRepository.getContributorById(id);
    }

    public Contributor create(CreateContributorRequest request) {
        Contributor contributor = Contributor.builder()
                .displayName(request.getDisplayName())
                .revoked(false)
                .registeredAt(LocalDateTime.now())
                .build();

        return contributorRepository.create(contributor);
    }
}
