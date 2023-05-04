package com.sgb.drones.domain.services;

import java.util.List;
import java.util.Optional;

import com.sgb.drones.domain.entities.Drone;
import com.sgb.drones.domain.enums.DroneStateEnum;

public interface DroneService {
    
    public void save(Drone drone);
    public Optional<Drone> getById(long id);
    public List<Drone> getByState(DroneStateEnum state);
}
