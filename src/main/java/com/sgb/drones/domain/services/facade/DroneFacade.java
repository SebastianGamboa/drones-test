package com.sgb.drones.domain.services.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sgb.drones.domain.constants.MessageConst;
import com.sgb.drones.domain.entities.Drone;
import com.sgb.drones.domain.enums.DroneStateEnum;
import com.sgb.drones.domain.services.DroneService;
import com.sgb.drones.web.dtos.DroneDTO;
import com.sgb.drones.web.exceptions.NotFoundException;

@Service
public class DroneFacade {
    
    private final DroneService droneService;

    public DroneFacade(DroneService droneService) {
        this.droneService = droneService;
    }

    public void save(Drone drone) {
        drone.validate();
        drone.initialState();
        this.droneService.save(drone);
    }

    public DroneDTO getById(Long id) {
        var existingDrone = droneService.getById(id);
        exist(existingDrone, id);
        var drone = existingDrone.get();
        return new DroneDTO(
            drone.getId(),
            drone.getSerialNumber(),
            drone.getModel(),
            drone.getMaxWeight(),
            drone.getBatteryLevel(),
            drone.getState()
        );
    }

    public List<DroneDTO> getByState(String state) {
        var stateEnum = DroneStateEnum.valueOf(state.toUpperCase());
        var drones = droneService.getByState(stateEnum);
        if (drones.isEmpty()) {
            throw new NotFoundException(
                HttpStatus.NOT_FOUND,
                String.format(MessageConst.DRONE_NOT_FOUND_ERROR, state)
            );
        }
        return drones.stream()
            .map(drone -> new DroneDTO(
                drone.getId(),
                drone.getSerialNumber(),
                drone.getModel(),
                drone.getMaxWeight(),
                drone.getBatteryLevel(),
                drone.getState()
            ))
            .collect(Collectors.toList());
    }

    private void exist(Optional<Drone> drone, long id) {
        if (!drone.isPresent()) {
            throw new NotFoundException(
                HttpStatus.NOT_FOUND,
                String.format(MessageConst.DRONE_NOT_FOUND_ERROR, id)
            );
        }
    }
}
