package com.platinumbench.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "benchmark_job")
@Entity 

public class BenchmarkJobEntity {

    @Id 
    @Column
    private Long jobId;

    @Column 
    private Long configId;

    @Column 
    private Long modelFileId;

    @Column
    private Long engineId;

    @Column 
    private Long promptSetId;

    @Column 
    private LocalDateTime createdAt;

    @Column 
    private String status;

    @Column 
    private int priority;
}
