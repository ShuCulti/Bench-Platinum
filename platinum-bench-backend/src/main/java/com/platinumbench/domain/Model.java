package com.platinumbench.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor 
@Getter
@Setter

public class Model {
    private Long id;
    private String name;
    private String family;
    private BigDecimal parameterB;
}