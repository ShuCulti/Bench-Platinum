package com.platinumbench.persistence.entity;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "contributor") 
@Entity 

public class ContributorEntity {

    @Id 
    @Column 
    private Long id;

    @Column 
    private String displayName;

    @Column 
    private String tokenHash;

    @Column 
    private String status;

    @Column 
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime revokedAt;
}
