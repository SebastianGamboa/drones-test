package com.sgb.drones.web.dtos;

import com.sgb.drones.domain.enums.DroneStateEnum;
import com.sgb.drones.domain.enums.ModelEnum;

public record DroneDTO(
    long id,
    String serialNumber,
    ModelEnum model,
    int maxWeight,
    int batteryLevel,
    DroneStateEnum state
) { }
