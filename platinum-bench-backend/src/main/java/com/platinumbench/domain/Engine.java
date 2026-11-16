package com.platinumbench.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor 

public class Engine {
    private Long id;
    private String name;
    private String version;
    private String buildCommit;
}
