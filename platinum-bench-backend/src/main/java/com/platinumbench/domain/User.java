package com.platinumbench.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor

public class User{
    private int id;
    private String email;
    private String userName;
    private String passwordHash;
    private LocalDateTime eventDateTime;

}