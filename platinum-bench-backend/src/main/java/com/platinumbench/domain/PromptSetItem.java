package com.platinumbench.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor 
@Getter 
@Setter 

public class PromptSetItem {
    private Long promptSetId;
    private Long promptId;
    private int position;
}
