package com.sgb.drones.domain.services.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sgb.drones.domain.entities.Item;
import com.sgb.drones.domain.entities.Shipping;
import com.sgb.drones.domain.entities.ShippingDetail;
import com.sgb.drones.domain.services.ShippingDetailService;

@Service
public class ShippingDetailFacade {

    private final ShippingDetailService shippingDetailService;

    public ShippingDetailFacade(ShippingDetailService shippingDetailService) {
        this.shippingDetailService = shippingDetailService;
    }

    public void createShipping(List<Item> items, Long shippingId) {
        var shippingDetails = items.stream()
            .map(item -> itemToShippingDetail(shippingId, item.getId()))
            .collect(Collectors.toList());
        shippingDetailService.saveShippingItems(shippingDetails);
    }

    private ShippingDetail itemToShippingDetail(Long shippingId, Long itemId) {
        return new ShippingDetail(
            new Shipping(shippingId),
            new Item(itemId)
        );
    }
}
