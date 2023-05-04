package com.sgb.drones.data;

import org.springframework.data.repository.CrudRepository;

import com.sgb.drones.domain.entities.Shipping;

public interface ShippingRepository extends CrudRepository<Shipping, Long> {
    
}
