package com.platinumbench.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor 
@Getter 
@Setter 

public class RunResult{
    private Long runId;
    private BigDecimal promptTps;
    private BigDecimal genTps;
    private int promptTokens;
    private int genTokens;
    private int loadMs;
    private int totalMs;
    private int vramPeakMb;
    private BigDecimal avgPowerW;
}