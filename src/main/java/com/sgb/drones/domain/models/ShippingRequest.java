package com.sgb.drones.domain.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingRequest {
    
    private Long droneId;
    private List<Long> itemIds;
}
