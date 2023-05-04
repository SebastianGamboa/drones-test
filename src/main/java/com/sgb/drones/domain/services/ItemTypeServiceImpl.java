package com.sgb.drones.domain.services;

import org.springframework.stereotype.Service;

import com.sgb.drones.data.ItemTypeRepository;
import com.sgb.drones.domain.entities.ItemType;

@Service
public class ItemTypeServiceImpl implements ItemTypeService {

    private final ItemTypeRepository itemTypeRepository;

    public ItemTypeServiceImpl(ItemTypeRepository itemTypeRepository) {
        this.itemTypeRepository = itemTypeRepository;
    }

    @Override
    public Long save(ItemType itemType) {
        return itemTypeRepository.save(itemType).getId();
    }
}
