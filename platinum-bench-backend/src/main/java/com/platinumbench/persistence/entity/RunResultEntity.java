package com.platinumbench.persistence.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "run_result")
@Entity 

public class RunResultEntity {

    @Id 
    @Column 
    private Long id;

    @Column 
    private BigDecimal promptTps;

    @Column 
    private BigDecimal genTps;

    @Column 
    private int promptTokens;

    @Column 
    private int genTokens;

    @Column 
    private int loadMs;

    @Column 
    private int totalMs;

    @Column 
    private int vramPeakMb;

    @Column
    private BigDecimal avgPowerW;

}
