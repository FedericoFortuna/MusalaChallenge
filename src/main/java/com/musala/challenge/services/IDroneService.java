package com.musala.challenge.services;

import com.musala.challenge.dtos.Drone;
import com.musala.challenge.dtos.Medication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDroneService {
    Drone createDrone(Drone drone);
    Drone loadDrone(String serialNumber, List<Medication> medications);
    List<Drone> getDronesForLoading();
    Integer getDroneBattery(String serialNumber);
}
