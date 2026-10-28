package com.platinumbench.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Entity
@Table(name= "model_file")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 

/* Hello  */

public class ModelFileEntity{

    @Id
    @Column(name = "model_file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long modelId;

    @Column(nullable = false)
    private String quant;

    @Column(nullable = false)
    private Long sizeBytes;

    @Column(nullable = false, unique = true, length = 64)
    private String sha256;

}
