package com.sgb.drones.domain.services;

import java.util.List;

import com.sgb.drones.domain.entities.Item;

public interface ItemService {
    
    public void save(Item item);
    public List<Item> getByIds(List<Long> ids);
}
