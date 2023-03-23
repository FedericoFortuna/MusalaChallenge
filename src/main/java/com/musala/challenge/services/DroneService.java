package com.musala.challenge.services;

import com.musala.challenge.conf.LoggingTag;
import com.musala.challenge.dtos.Drone;
import com.musala.challenge.dtos.Medication;
import com.musala.challenge.exceptions.SerialNumberNotFoundException;
import com.musala.challenge.mappers.DroneMapper;
import com.musala.challenge.repositories.DroneRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DroneService implements IDroneService {

    private String LOG_VALIDATING_NULL_FIELDS = "{} - {}: Validating if any field is null.";
    private String LOG_ANY_FIELD_IS_NULL = "{} - {}: Any field is null.";

    private org.slf4j.Logger logger = LoggerFactory.getLogger("DroneServiceLogger");

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneMapper droneMapper;

    @Override
    public Drone createDrone(Drone drone) {
        logger.info(LOG_VALIDATING_NULL_FIELDS, LoggingTag.SERVICE_EXECUTION, DroneService.class);
        drone.validate();
        logger.info(LOG_ANY_FIELD_IS_NULL, LoggingTag.SERVICE_EXECUTION, DroneService.class);
        return droneMapper.map(droneRepository.save(droneMapper.map(drone)));
    }

    @Override
    public Drone loadDrone(String serialNumber, List<Medication> medications) {
        Optional<com.musala.challenge.entities.Drone> droneEntity = droneRepository.findById(serialNumber);
        // TODO hacer validation para medication tambien


        if (droneEntity.isEmpty()) {
            throw new SerialNumberNotFoundException();
        }
        Drone drone = droneMapper.map(droneEntity.get());

        for (Medication medication : medications) {
            drone.getMedications().add(medication);
        }

        return drone;

    }
}
