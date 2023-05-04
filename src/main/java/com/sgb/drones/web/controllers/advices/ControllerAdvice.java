package com.sgb.drones.web.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sgb.drones.domain.exceptions.DroneException;
import com.sgb.drones.domain.exceptions.ItemException;
import com.sgb.drones.web.dtos.ResponseError;
import com.sgb.drones.web.exceptions.NotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseError> handleNotFound(NotFoundException notFoundException) {
        return new ResponseEntity<ResponseError>(
            new ResponseError(notFoundException.getMessage()),
            notFoundException.getCode()
        );
    }

    @ExceptionHandler(DroneException.class)
    public ResponseEntity<ResponseError> handleModelValidation(DroneException droneException) {
        return new ResponseEntity<ResponseError>(
            new ResponseError(droneException.getMessage()),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ResponseError> handlePrestamoException(ItemException itemException) {
        return new ResponseEntity<ResponseError>(
            new ResponseError(itemException.getMessage()),
            itemException.getCode()
        );
    }
}
