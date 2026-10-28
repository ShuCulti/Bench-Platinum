package com.platinumbench.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor 
@Getter 
@Setter 

public class Device {
    private Long id;
    private Long machineId;
    private String deviceType;
    private String name;
    private String vendor;
    private String driverVersion;
    private Integer vramMb;
    private Integer cores;
}
