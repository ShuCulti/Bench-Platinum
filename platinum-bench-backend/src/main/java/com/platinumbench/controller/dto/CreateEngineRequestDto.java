package com.platinumbench.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreateEngineRequestDto(
    @NotBlank String name, 
    @NotBlank String version, 
    @NotEmpty  String buildCommit) {}
