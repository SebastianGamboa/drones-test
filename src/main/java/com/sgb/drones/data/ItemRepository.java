package com.sgb.drones.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sgb.drones.domain.entities.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
    
    public List<Item> findByIdIn(List<Long> ids);
}
