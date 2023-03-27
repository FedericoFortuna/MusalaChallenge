package com.musala.challenge.services;

import com.musala.challenge.conf.LoggingTag;
import com.musala.challenge.dtos.Drone;
import com.musala.challenge.dtos.Medication;
import com.musala.challenge.enums.DroneState;
import com.musala.challenge.exceptions.SerialNumberNotFoundException;
import com.musala.challenge.exceptions.StateNotAllowedToLoadException;
import com.musala.challenge.mappers.DroneMapper;
import com.musala.challenge.repositories.DroneRepository;
import com.musala.challenge.repositories.MedicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class DroneService implements IDroneService {

    private final String LOG_VALIDATING_NULL_FIELDS = "{} {} - {}: Validating if any field is null.";
    private final String LOG_ANY_FIELD_IS_NULL = "{} {} - {}: Any field is null.";
    private final String VALIDATING_SERIAL_NUMBER = "{} {} - {} Validating serial number";
    private final String ERROR_VALIDATING_SERIAL_NUMBER = "{} {} - {} Error validating serial number {}";
    private final String VALIDATING_DRONE_STATE = "{} {} - {} Validating Drone State";
    private final String VALIDATING_MEDICATION_TO_LOAD_DRONE = "{} {} - {} Validating medication to load drone.";
    private final String LOADING_DRONE_WITH_MEDICATIONS = "{} {} - {} Loading drone with medications";
    private final String SAVING_DRONE = "{} {} - {} Saving drone: '{}'";

    private Logger logger = LoggerFactory.getLogger(DroneService.class);


    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneMapper droneMapper;

    @Autowired
    private MedicationRepository medicationRepository;


    @Override
    public Drone createDrone(Drone drone) {
        logger.info(LOG_VALIDATING_NULL_FIELDS, LocalDateTime.now(), LoggingTag.SERVICE_EXECUTION, DroneService.class.getSimpleName());
        drone.validate();
        logger.info(LOG_ANY_FIELD_IS_NULL, LocalDateTime.now(), LoggingTag.SERVICE_EXECUTION, DroneService.class.getSimpleName());
        logger.info(SAVING_DRONE, LocalDateTime.now(), LoggingTag.SERVICE_EXECUTION, DroneService.class.getSimpleName(), drone.toString());
        return droneMapper.toDto(droneRepository.save(droneMapper.toEntity(drone)));
    }

    @Override
    public Drone loadDrone(String serialNumber, List<Medication> medications) {
        Optional<com.musala.challenge.entities.Drone> droneEntity = droneRepository.findById(serialNumber);
        logger.info(VALIDATING_SERIAL_NUMBER, LocalDateTime.now(), LoggingTag.SERVICE_EXECUTION, DroneService.class.getSimpleName());
        if (droneEntity.isEmpty()) {
            logger.info(ERROR_VALIDATING_SERIAL_NUMBER, LocalDateTime.now(), LoggingTag.SERVICE_EXECUTION, DroneService.class.getSimpleName(), serialNumber);
            throw new SerialNumberNotFoundException();
        }

        Drone drone = droneMapper.toDto(droneEntity.get());

        logger.info(VALIDATING_DRONE_STATE, LocalDateTime.now(), LoggingTag.SERVICE_EXECUTION, DroneService.class.getSimpleName());
        if (!Objects.equals(drone.getDroneState(), DroneState.IDLE.toString())) {
            throw new StateNotAllowedToLoadException();
        }

        logger.info(VALIDATING_MEDICATION_TO_LOAD_DRONE, LocalDateTime.now(), LoggingTag.SERVICE_EXECUTION, DroneService.class.getSimpleName());
        for (Medication med : medications) {
            med.validate();
            med.droneLink(drone);
        }

        logger.info(LOADING_DRONE_WITH_MEDICATIONS, LocalDateTime.now(), LoggingTag.SERVICE_EXECUTION, DroneService.class.getSimpleName());
        drone.loadDrone(medications);


        logger.info(SAVING_DRONE, LocalDateTime.now(), LoggingTag.SERVICE_EXECUTION, DroneService.class.getSimpleName(), drone.toString());
        droneRepository.save(droneMapper.toEntity(drone));

        return drone;
    }

    @Override
    public List<Drone> getDronesForLoading() {
        Optional<List<com.musala.challenge.entities.Drone>> optDronesEntity = droneRepository.findByState(DroneState.IDLE);
        List<Drone> drones = new ArrayList<>();

        if (optDronesEntity.isPresent()) {
            List<com.musala.challenge.entities.Drone> droneEntityList = optDronesEntity.get();
            droneEntityList.forEach(drone -> drones.add(droneMapper.toDto(drone)));
        }


        return drones;
    }

    @Override
    public Integer getDroneBattery(String serialNumber) {
        Optional<com.musala.challenge.entities.Drone> droneEntity = droneRepository.findById(serialNumber);

        if (droneEntity.isEmpty()) {
            throw new SerialNumberNotFoundException();
        }

        return droneMapper.toDto(droneEntity.get()).getBatteryCapacity();
    }

    @Override
    public void changeStatusToLoading(String serialNumber) {
        Optional<com.musala.challenge.entities.Drone> droneEntity = droneRepository.findById(serialNumber);

        if (droneEntity.isEmpty()) {
            throw new SerialNumberNotFoundException();
        }
        Drone drone = droneMapper.toDto(droneEntity.get());
        drone.changeStatusToLoading();
        droneRepository.save(droneMapper.toEntity(drone));
    }
}
