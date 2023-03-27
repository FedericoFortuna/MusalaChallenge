package com.musala.challenge.services;

import com.musala.challenge.dtos.Medication;
import com.musala.challenge.entities.Drone;
import com.musala.challenge.enums.DroneState;
import com.musala.challenge.exceptions.DroneIsNotLoadedException;
import com.musala.challenge.exceptions.SerialNumberNotFoundException;
import com.musala.challenge.mappers.DroneMapper;
import com.musala.challenge.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService implements IMedicationService{

    @Autowired
    private DroneMapper droneMapper;

     @Autowired
     private DroneRepository droneRepository;

    @Override
    public List<Medication> checkLoadedMedication(String serialNumberDrone) {
        Optional<Drone> droneEntity = droneRepository.findById(serialNumberDrone);

        if (droneEntity.isEmpty()) {
            throw new SerialNumberNotFoundException();
        }


        com.musala.challenge.dtos.Drone drone = droneMapper.toDto(droneEntity.get());

        return drone.getMedications();
    }
}
