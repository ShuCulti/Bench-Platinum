package com.platinumbench.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter

public class Contributor{
    private Long id;
    private String displayName;
    private String tokenHash;
    private boolean revoked;
    private LocalDateTime registeredAt;
    
}

