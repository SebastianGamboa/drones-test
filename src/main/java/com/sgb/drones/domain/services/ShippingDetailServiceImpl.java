package com.sgb.drones.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sgb.drones.data.ShippingDetailRepository;
import com.sgb.drones.domain.entities.ShippingDetail;

@Service
public class ShippingDetailServiceImpl implements ShippingDetailService {

    private final ShippingDetailRepository shippingDetailRepository;

    public ShippingDetailServiceImpl(ShippingDetailRepository shippingDetailRepository) {
        this.shippingDetailRepository = shippingDetailRepository;
    }

    @Override
    public void saveShippingItems(List<ShippingDetail> shippingDetails) {
        shippingDetailRepository.saveAll(shippingDetails);
    }
}
