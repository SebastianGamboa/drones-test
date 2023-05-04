package com.sgb.drones.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sgb.drones.data.DroneRepository;
import com.sgb.drones.domain.entities.Drone;
import com.sgb.drones.domain.enums.DroneStateEnum;

@Service
public class DroneServiceImpl implements DroneService {

    private DroneRepository droneRepository;

    public DroneServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public void save(Drone drone) {
        droneRepository.save(drone);
    }

    @Override
    public Optional<Drone> getById(long id) {
        return droneRepository.findById(id);
    }

    @Override
    public List<Drone> getByState(DroneStateEnum state) {
       return droneRepository.findByState(state);
    }
}
