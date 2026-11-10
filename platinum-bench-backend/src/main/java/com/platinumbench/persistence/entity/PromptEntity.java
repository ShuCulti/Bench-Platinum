package com.platinumbench.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "prompt") 
@Entity 
public class PromptEntity {
    
    @Id 
    @Column
    private Long promptId;

    @Column 
    private String promptText;
    
    @Column 
    private String expectedAnswer;
}
