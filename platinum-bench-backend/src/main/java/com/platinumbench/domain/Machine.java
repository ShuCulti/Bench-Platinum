package com.platinumbench.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor 
@Getter 
@Setter 

public class Machine {
    private Long id;
    private Long contributorId;
    private String hostname;
    private String os;
    private String agentVersion;
    private LocalDateTime lastSeenAt;

}
