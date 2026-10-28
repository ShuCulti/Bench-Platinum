package com.platinumbench.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 

public class BenchmarkRun {
    private Long id;
    private Long jobId;
    private Long deviceId;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private String status;
    private String errorMessage;
}
