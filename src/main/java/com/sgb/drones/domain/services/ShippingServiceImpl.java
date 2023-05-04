package com.sgb.drones.domain.services;

import org.springframework.stereotype.Service;

import com.sgb.drones.data.ShippingRepository;
import com.sgb.drones.domain.entities.Shipping;

@Service
public class ShippingServiceImpl implements ShippingService {

    private final ShippingRepository shippingRepository;

    public ShippingServiceImpl(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    @Override
    public Long save(Shipping shipping) {
        return shippingRepository.save(shipping).getId();
    }
}
