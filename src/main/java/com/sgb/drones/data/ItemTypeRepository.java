package com.sgb.drones.data;

import org.springframework.data.repository.CrudRepository;

import com.sgb.drones.domain.entities.ItemType;

public interface ItemTypeRepository extends CrudRepository<ItemType, Long> {
    
}
