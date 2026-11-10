package com.platinumbench.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "run_config_flag")
@Entity 
public class RunConfigFlagEntity {

    @Id 
    @Column
    private Long id;

    @Column
    private String flagKey;

    @Column
    private String flagValue;

}
