package com.platinumbench.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 

public class RunConfig {
    private Long id;
    private int ngpuLayers;
    private int ctxSize;
    private int batchSize;
    private boolean flashAttn;
    private int threads;
}
