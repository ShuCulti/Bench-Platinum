package com.platinumbench.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor 
@Getter 
@Setter 

public class Prompt {
    private Long id;
    private String promptText;
    private String expectedAnswer;
}
