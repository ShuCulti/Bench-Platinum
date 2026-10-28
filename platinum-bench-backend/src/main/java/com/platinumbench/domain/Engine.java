package com.platinumbench.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor  

public class Engine {
    private Long id;
    private String name;
    private String version;
    private String buildCommit;
}
