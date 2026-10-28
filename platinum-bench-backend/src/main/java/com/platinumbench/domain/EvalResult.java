package com.platinumbench.domain;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 

public class EvalResult {
    private Long  id;
    private Long  runId;
    private Long promptId;
    private String outputText; 
    private BigDecimal score;
    private int latencyMs;
}
