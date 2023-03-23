package com.musala.challenge.mappers;

import com.musala.challenge.entities.Drone;
import com.musala.challenge.enums.DroneModel;
import com.musala.challenge.enums.DroneState;
import org.springframework.stereotype.Service;

@Service
public class DroneMapper {
    public Drone map(com.musala.challenge.dtos.Drone drone){
        return Drone.builder()
                .batteryCapacity(drone.getBatteryCapacity())
                .model(DroneModel.valueOf(drone.getModel().toUpperCase()))
                .serialNumber(drone.getNumber())
                .weightLimit(drone.getWeightLimit())
                .state(DroneState.valueOf(drone.getDroneState().toUpperCase()))
                .build();
    }

    public com.musala.challenge.dtos.Drone map(Drone drone) {
        return com.musala.challenge.dtos.Drone.builder()
                .droneState(drone.getState().toString())
                .batteryCapacity(drone.getBatteryCapacity())
                .number(drone.getSerialNumber())
                .model(drone.getModel().toString())
                .weightLimit(drone.getWeightLimit())
                .build();
    }

}
