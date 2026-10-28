package com.platinumbench.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ModelFile{
    private Long id;
    private Long modelId;
    private String quant;
    private Long sizeBytes;
    private String sha256;    
}