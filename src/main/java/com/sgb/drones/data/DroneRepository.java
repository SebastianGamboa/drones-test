package com.sgb.drones.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sgb.drones.domain.entities.Drone;
import com.sgb.drones.domain.enums.DroneStateEnum;

@Repository
public interface DroneRepository extends CrudRepository<Drone, Long> {
    
    public List<Drone> findByState(DroneStateEnum state);
}
