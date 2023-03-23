package com.musala.challenge.exceptions;

import com.musala.challenge.dtos.Drone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value(value = "Any field can be null")
    private String fieldNull;

    @Value(value = "The entered model is not valid")
    private String droneModelNotFound;

    @Value(value = "The entered state is not valid")
    private String droneStateNotFound;

    @Value(value = "The weight must be lower than ")
    private String weightLimitEx;

    @Value(value = "The battery capacity must be between 0 and 100 ")
    private String batteryCapacity;

    @Value(value = "The serial number entered does not exist")
    private String serialNumberNotFound;


    @ExceptionHandler(value = NullFieldException.class)
    public ResponseEntity nullFieldException(NullFieldException e){
        return new ResponseEntity(fieldNull, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DroneModelNotFoundException.class)
    public ResponseEntity droneModelNotFoundException(DroneModelNotFoundException e){
        return new ResponseEntity(droneModelNotFound, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DroneStateNotFoundException.class)
    public ResponseEntity droneStateNotFoundException(DroneStateNotFoundException e){
        return new ResponseEntity(droneStateNotFound, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = WeightLimitExceededException.class)
    public ResponseEntity weightLimitExceededException(WeightLimitExceededException e){
        return new ResponseEntity(weightLimitEx + Drone.WEIGHT_LIMIT, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BatteryCapacityException.class)
    public ResponseEntity batteryCapacityException(BatteryCapacityException e){
        return new ResponseEntity(batteryCapacity, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SerialNumberNotFoundException.class)
    public ResponseEntity serialNumberNotFoundException(SerialNumberNotFoundException e){
        return new ResponseEntity(serialNumberNotFound, HttpStatus.BAD_REQUEST);
    }

}
