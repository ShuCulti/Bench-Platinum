package com.platinumbench.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name= "device")
@Entity 
public class DeviceEntity {
    @Id 
    @Column
    private Long id;

    @Column 
    private Long machineId;

    @Column 
    private String deviceType;

    @Column 
    private String name;

    @Column
    private String vendor;

    @Column(nullable = false)
    private String driverVersion;
    
    @Column(nullable = false)
    private Integer vramMb;

    @Column
    private Integer cores;

}
