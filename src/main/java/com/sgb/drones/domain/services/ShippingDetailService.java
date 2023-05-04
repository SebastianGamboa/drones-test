package com.sgb.drones.domain.services;

import java.util.List;

import com.sgb.drones.domain.entities.ShippingDetail;

public interface ShippingDetailService {
    
    public void saveShippingItems(List<ShippingDetail> shippingDetails);
}
