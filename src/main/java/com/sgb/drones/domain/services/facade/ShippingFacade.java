package com.sgb.drones.domain.services.facade;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sgb.drones.domain.constants.DroneConst;
import com.sgb.drones.domain.constants.MessageConst;
import com.sgb.drones.domain.entities.Drone;
import com.sgb.drones.domain.entities.Item;
import com.sgb.drones.domain.entities.Shipping;
import com.sgb.drones.domain.exceptions.DroneException;
import com.sgb.drones.domain.exceptions.ItemException;
import com.sgb.drones.domain.models.ShippingRequest;
import com.sgb.drones.domain.services.ItemService;
import com.sgb.drones.domain.services.ShippingService;

@Service
public class ShippingFacade {

    private final DroneFacade droneFacade;
    private final ShippingDetailFacade shippingDetailFacade;
    private final ShippingService shippingService;
    private final ItemService itemService;

    public ShippingFacade(
        DroneFacade droneFacade,
        ShippingDetailFacade shippingDetailFacade,
        ShippingService shippingService,
        ItemService itemService
    ) {
        this.droneFacade = droneFacade;
        this.shippingDetailFacade = shippingDetailFacade;
        this.shippingService = shippingService;
        this.itemService = itemService;
    }

    public void createShipping(ShippingRequest shippingRequest) {
        var droneDto = droneFacade.getById(shippingRequest.getDroneId());
        var drone = new Drone(droneDto.id());
        var shipping = new Shipping(drone);
        
        var items = itemService.getByIds(shippingRequest.getItemIds());
        
        validateItems(items);
        validateItemsWeight(items, droneDto.maxWeight());
        validateDroneBatteryLevel(droneDto.batteryLevel());

        Long shippingId = shippingService.save(shipping);

        shippingDetailFacade.createShipping(items, shippingId);
    }

    private void validateItems(List<Item> items) {
        if (items.isEmpty()) {
            throw new ItemException(
                HttpStatus.BAD_REQUEST,
                String.format(MessageConst.ITEMS_NOT_FOUND, items.toString())
            );
        }
    }

    private void validateItemsWeight(List<Item> items, int droneMaxWeight) {
        int totalWeight = items.stream().mapToInt(Item::getWeight).sum();
        if (totalWeight > droneMaxWeight) {
            throw new DroneException(
                HttpStatus.BAD_REQUEST,
                String.format(MessageConst.MAX_ITEM_WEIGHT_EXCEEDED_ERROR, droneMaxWeight)
            );
        }
    }

    private void validateDroneBatteryLevel(int batteryLevel) {
        if (batteryLevel < DroneConst.MIN_BATTERY_LEVEL) {
            throw new DroneException(
                HttpStatus.BAD_REQUEST,
                String.format(MessageConst.MIN_BATTERY_LEVEL_ERROR, batteryLevel)
            );
        }
    }
}
