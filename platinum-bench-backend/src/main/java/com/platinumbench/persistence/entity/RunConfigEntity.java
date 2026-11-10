package com.platinumbench.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "run_config")
@Entity 
public class RunConfigEntity{

    @Id 
    @Column 
    private Long id;

    @Column 
    private int nGpuLayers;

    @Column 
    private int ctxSize;

    @Column 
    private int batchSize;

    @Column 
    private boolean flashAttn;

    @Column 
    private int threads;

}

