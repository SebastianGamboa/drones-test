package com.sgb.drones.domain.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemException extends RuntimeException {
    
    private HttpStatus code;
    private String message;
}
