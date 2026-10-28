package com.platinumbench.persistence.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "model")
@Getter
@Setter
@NoArgsConstructor

public class ModelEntity{

    @Id
    @Column(name= "model_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String family;

    @Column(nullable= false, precision = 6, scale = 2)
    private BigDecimal parameterB;
}