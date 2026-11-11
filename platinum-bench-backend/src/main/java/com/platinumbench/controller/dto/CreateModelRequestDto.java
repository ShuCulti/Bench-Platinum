package com.platinumbench.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.DecimalMax;


public record CreateModelRequestDto(
    @NotBlank String name, 
    @NotBlank String family, 
    @NotNull @Positive @DecimalMax("9999.99") BigDecimal parameterB) {}
