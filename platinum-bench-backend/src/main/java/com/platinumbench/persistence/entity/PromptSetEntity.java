package com.platinumbench.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "prompt_set")
@Entity 

public class PromptSetEntity {
    @Id 
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;
}
