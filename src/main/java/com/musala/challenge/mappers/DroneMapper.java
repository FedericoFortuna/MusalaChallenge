package com.musala.challenge.mappers;

import com.musala.challenge.entities.Drone;
import org.springframework.stereotype.Service;

@Service
public class DroneMapper {
    public Drone map(com.musala.challenge.dtos.Drone drone){
        return Drone.builder()
                .batteryCapacity(drone.getBatteryCapacity())
                .model(drone.getModel().toString())
                .serialNumber(drone.getNumber())
                .weightLimit(drone.getWeightLimit())
                .state(drone.getDroneState().toString())
                .build();
    }
}
