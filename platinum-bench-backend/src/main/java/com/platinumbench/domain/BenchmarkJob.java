package com.platinumbench.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 

public class BenchmarkJob {
    private Long id;
    private Long configId;
    private Long modelFileId;
    private Long engineId;
    private Long promptSetId;
    private LocalDateTime createdAt;
    private String status;
    private int priority;
}
