package com.platinumbench.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Model {
    private Long id;
    private String name;
    private String family;
    private int parameterB;
}