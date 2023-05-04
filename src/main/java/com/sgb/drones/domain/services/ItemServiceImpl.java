package com.sgb.drones.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sgb.drones.data.ItemRepository;
import com.sgb.drones.domain.entities.Item;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void save(Item item) {
        item.validate();
        itemRepository.save(item);
    }

    @Override
    public List<Item> getByIds(List<Long> ids) {
        return itemRepository.findByIdIn(ids);
    }
    
}
