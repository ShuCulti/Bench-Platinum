package com.platinumbench.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Table(name = "eval_result")
@Entity 

public class EvalResult {
    @Id 
    @Column 
    private Long id;
    
    @Column 
    private Long runId;

    @Column 
    private Long promptId;

    @Column 
    private String outputText;

    @Column 
    private BigDecimal score;

    @Column 
    private int latencyMs;

}
