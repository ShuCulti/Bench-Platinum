package com.platinumbench.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor 
@Getter 
@Setter 

public class RunConfigFlag {
    private Long configId;
    private String flagKey;
    private String flagValue;
}
