package com.sgb.drones.domain.entities;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.sgb.drones.domain.constants.DroneConst;
import com.sgb.drones.domain.constants.MessageConst;
import com.sgb.drones.domain.enums.DroneStateEnum;
import com.sgb.drones.domain.enums.ModelEnum;
import com.sgb.drones.domain.exceptions.DroneException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Drone {

    private final int INITIAL_BATTERY_LEVEL = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModelEnum model;

    @Column(nullable = false)
    private int maxWeight;

    private int batteryLevel;

    @Enumerated(EnumType.STRING)
    private DroneStateEnum state;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    public Drone(long id) {
        this.id = id;
    }

    public void validate() {
        validateSerialNumber();
        validateWeight();
    }

    private void validateSerialNumber() {
        if (serialNumber.length() > DroneConst.MAX_CHARS_FOR_SERIAL_NUMBER) {
            throw new DroneException(
                HttpStatus.BAD_REQUEST,
                String.format(MessageConst.MAX_CHARS_SERIAL_NUMBER_ERROR, serialNumber)
            );
        }
    }

    private void validateWeight() {
        if (maxWeight > DroneConst.MAX_WEIGHT) {
            throw new DroneException(
                HttpStatus.BAD_REQUEST,
                String.format(MessageConst.MAX_WEIGHT_ERROR, maxWeight)
            );
        }
    }

    public void initialState() {
        this.state = DroneStateEnum.IDLE;
        this.batteryLevel = INITIAL_BATTERY_LEVEL;
    }
}
