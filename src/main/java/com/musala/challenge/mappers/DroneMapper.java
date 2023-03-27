package com.musala.challenge.mappers;

import com.musala.challenge.dtos.Medication;
import com.musala.challenge.entities.Drone;
import com.musala.challenge.enums.DroneModel;
import com.musala.challenge.enums.DroneState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneMapper {

    public Drone toEntity(com.musala.challenge.dtos.Drone drone) {
        return Drone.builder()
                .batteryCapacity(drone.getBatteryCapacity())
                .model(DroneModel.valueOf(drone.getModel().toUpperCase()))
                .serialNumber(drone.getNumber())
                .weightLimit(drone.getWeightLimit())
                .state(DroneState.valueOf(drone.getDroneState().toUpperCase()))
                .medications(drone.getMedications().stream().map(this::medicationDtoToEntity).collect(Collectors.toList()))
                .build();
    }

    public com.musala.challenge.dtos.Drone toDto(Drone droneEntity) {
        return com.musala.challenge.dtos.Drone.builder()
                .droneState(droneEntity.getState().toString())
                .batteryCapacity(droneEntity.getBatteryCapacity())
                .number(droneEntity.getSerialNumber())
                .model(droneEntity.getModel().toString())
                .weightLimit(droneEntity.getWeightLimit())
                .medications(droneEntity.getMedications().stream().map(this::medicationEntityToDto).collect(Collectors.toList()))
                .build();
    }

    private com.musala.challenge.entities.Medication medicationDtoToEntity(Medication med){
        com.musala.challenge.dtos.Drone drone = med.getDrone();

        return com.musala.challenge.entities.Medication.builder()
                .id(med.getId())
                .code(med.getCode())
                .weight(med.getWeight())
                .image(med.getImage())
                .drone(com.musala.challenge.entities.Drone.builder()
                        .serialNumber(drone.getNumber())
                        .build())
                .build();
    }

    private Medication medicationEntityToDto(com.musala.challenge.entities.Medication med){
        Drone droneEntity = med.getDrone();
        return Medication.builder()
                .id(med.getId())
                .code(med.getCode())
                .weight(med.getWeight())
                .image(med.getImage())
                .drone(com.musala.challenge.dtos.Drone.builder()
                        .number(droneEntity.getSerialNumber())
                        .build())
                .build();
    }

}
