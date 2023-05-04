package com.sgb.drones.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sgb.drones.domain.entities.ItemType;
import com.sgb.drones.domain.services.ItemTypeService;

@RestController
@RequestMapping("api/item-type")
public class ItemTypeController {
    
    private final ItemTypeService itemTypeService;

    public ItemTypeController(ItemTypeService itemTypeService) {
        this.itemTypeService = itemTypeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ItemType itemType) {
        itemTypeService.save(itemType);
    }
}
