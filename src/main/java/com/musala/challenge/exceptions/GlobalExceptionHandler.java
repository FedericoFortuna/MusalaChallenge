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

    @Value(value = "The weight of the medicines exceeds the limit weight of the drone.")
    private String medicinesWeightExceeded;

    @Value(value = "Only letters, numbers, '-', and '_' are allowed in Id Medication.")
    private String idRegexMedication;

    @Value(value = "Only upper case letters, underscore, and numbers are allowed in Code Medication.")
    private String codeRegexMedication;

    @Value(value = "The drone does not allow to be loaded because of his state.")
    private String stateNotAllowd;

    @Value(value = "The state drone is not loaded.")
    private String isNotLoadedDrone;

    @Value(value = "The drone's battery is below 25%")
    private String lowBatteryLevel;

    @ExceptionHandler(value = CanNotLoadDroneException.class)
    public ResponseEntity canNotLoadDroneException(CanNotLoadDroneException e){
        return new ResponseEntity(lowBatteryLevel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DroneIsNotLoadedException.class)
    public ResponseEntity droneIsNotLoadedException(DroneIsNotLoadedException e){
        return new ResponseEntity(isNotLoadedDrone, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RegexIdMedicationException.class)
    public ResponseEntity regexIdMedicationException(RegexIdMedicationException e){
        return new ResponseEntity(idRegexMedication, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RegexCodeMedicationException.class)
    public ResponseEntity regexCodeMedicationException(RegexCodeMedicationException e){
        return new ResponseEntity(codeRegexMedication, HttpStatus.BAD_REQUEST);
    }

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

    @ExceptionHandler(value = TotalWeightExceededException.class)
    public ResponseEntity totalWeightExceededException(TotalWeightExceededException e){
        return new ResponseEntity(medicinesWeightExceeded, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = StateNotAllowedToLoadException.class)
    public ResponseEntity stateNotAllowedToLoadException(StateNotAllowedToLoadException e){
        return new ResponseEntity(stateNotAllowd, HttpStatus.BAD_REQUEST);
    }

}
