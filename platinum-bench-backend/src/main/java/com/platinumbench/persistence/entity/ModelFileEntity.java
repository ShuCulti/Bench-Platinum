package com.platinumbench.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.springframework.data.convert.EntityConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Entity
@Table(name= "model_files")
@Getter
@Setter
@AllArgsConstructor

public class ModelFileEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long modelId;

    @Column
    private String quant;

    @Column
    private Long size_bytes;

    @Column
    private String sha256;

}
