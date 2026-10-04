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
    Long id;
    Long modelId;
    String quant;
    String sizeBytes;

    
}