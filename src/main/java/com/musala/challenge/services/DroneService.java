package com.musala.challenge.services;

import com.musala.challenge.dtos.Drone;
import com.musala.challenge.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DroneService implements IDroneService{

    @Autowired
    private DroneRepository droneRepository;

    @Override
    public Drone createDrone(Drone drone) {
        return null;
    }
}
