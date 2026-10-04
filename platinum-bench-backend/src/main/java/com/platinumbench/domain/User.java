package com.platinumbench.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter

public class User{
    private int id;
    private String email;
    private String userName;
    private String passwordHash;
    private LocalDateTime eventDateTime;

}