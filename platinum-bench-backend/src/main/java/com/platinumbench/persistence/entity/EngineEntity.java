package com.platinumbench.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "engine")
@Entity 
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 

public class EngineEntity {
    @Id 
    @Column 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column 
    private String name;

    @Column 
    private String version;

    @Column 
    private String buildCommit;

}
