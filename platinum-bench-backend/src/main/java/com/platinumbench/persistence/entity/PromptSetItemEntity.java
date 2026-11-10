package com.platinumbench.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "prompt_set_item")
@Entity 
public class PromptSetItemEntity {

    @Id 
    @Column()
    private Long promptSetId;

    @Column
    private Long promptId;

    @Column 
    private int position;
    
}
