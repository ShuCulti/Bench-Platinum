package com.platinumbench.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "benchmark_run")
@Entity 
public class BenchmarkRunEntity {
    @Id 
    @Column 
    private Long id;

    @Column 
    private Long jobId;

    @Column 
    private Long deviceId;

    @Column 
    private LocalDateTime startedAt;

    @Column(nullable = true)
    private LocalDateTime finishedAt;

    @Column 
    private String status;

    @Column(nullable = true)
    private String errorMessage;
}
