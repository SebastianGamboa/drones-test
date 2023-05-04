package com.sgb.drones.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sgb.drones.domain.entities.Drone;
import com.sgb.drones.domain.services.facade.DroneFacade;
import com.sgb.drones.web.dtos.DroneDTO;

@RestController
@RequestMapping("api/drone")
public class DroneController {
    
    private final DroneFacade droneFacade;

    public DroneController(DroneFacade droneFacade) {
        this.droneFacade = droneFacade;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DroneDTO getById(@PathVariable long id) {
        return droneFacade.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DroneDTO> getByState(@RequestParam String state) {
        return droneFacade.getByState(state);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Drone drone) {
        droneFacade.save(drone);
    }
}
