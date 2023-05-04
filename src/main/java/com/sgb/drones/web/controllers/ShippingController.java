package com.sgb.drones.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sgb.drones.domain.models.ShippingRequest;
import com.sgb.drones.domain.services.facade.ShippingFacade;

@RestController
@RequestMapping("api/shipping")
public class ShippingController {
    
    private final ShippingFacade shippingFacade;

    public ShippingController(ShippingFacade shippingFacade) {
        this.shippingFacade = shippingFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createShipping(@RequestBody ShippingRequest shippingRequest) {
        shippingFacade.createShipping(shippingRequest);
    }
    
}
