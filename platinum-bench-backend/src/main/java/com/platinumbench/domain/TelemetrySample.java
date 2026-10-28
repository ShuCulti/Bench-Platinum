package com.platinumbench.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor 
@Getter 
@Setter 

public class TelemetrySample {
    private Long runId;
    private LocalDateTime sampledAt;
    private int gpuUtilPct;
    private int vramUsedMb;
    private BigDecimal powerW;
    private BigDecimal tempC;
}
