package com.sgb.drones.domain.enums;

public enum DroneStateEnum {
    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    RETURNING("RETURNING");


    private String value;

    private DroneStateEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
