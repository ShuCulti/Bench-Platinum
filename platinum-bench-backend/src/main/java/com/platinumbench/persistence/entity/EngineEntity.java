package com.platinumbench.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "engine")
@Entity 

public class EngineEntity {
    @Id 
    @Column 
    private Long id;

    @Column 
    private String name;

    @Column 
    private String version;

    @Column 
    private String buildCommit;

}
