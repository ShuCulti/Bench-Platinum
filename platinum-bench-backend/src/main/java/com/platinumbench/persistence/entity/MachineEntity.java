package com.platinumbench.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "machine") 
@Entity 

public class MachineEntity {
    @Id
    @Column
    private Long id;

    @Column 
    private Long contributorId;

    @Column 
    private String hostName;

    @Column 
    private String os;

    @Column 
    private String agentVersion;

    @Column 
    private LocalDateTime timestamp;
    
}
