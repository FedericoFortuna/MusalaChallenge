package com.musala.challenge.service;

import com.musala.challenge.dtos.Drone;
import com.musala.challenge.dtos.Medication;
import com.musala.challenge.enums.DroneModel;
import com.musala.challenge.enums.DroneState;
import com.musala.challenge.mappers.DroneMapper;
import com.musala.challenge.repositories.DroneRepository;
import com.musala.challenge.repositories.MedicationRepository;
import com.musala.challenge.services.DroneService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DroneServiceTest {
    @Mock
    private DroneRepository droneRepository;

    @Mock
    private DroneMapper droneMapper;

    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private DroneService droneService;

    private Drone drone;

    private List<Medication> medicationList = new ArrayList<>();
    private List<com.musala.challenge.entities.Drone> droneEntityList = new ArrayList<>();
    private com.musala.challenge.entities.Drone droneEntity;

    @BeforeEach
    void init() {
        drone = Drone.builder()
                .droneState(DroneState.IDLE.toString())
                .batteryCapacity(10)
                .number("1")
                .model(DroneModel.HEAVYWEIGHT.toString())
                .weightLimit(1.0)
                .medications(new ArrayList<>())
                .build();

        droneEntity = com.musala.challenge.entities.Drone.builder()
                .state(DroneState.IDLE)
                .batteryCapacity(10)
                .serialNumber("1")
                .model(DroneModel.HEAVYWEIGHT)
                .weightLimit(1.0)
                .medications(new ArrayList<>())
                .build();

        Medication med = Medication.builder()
                .id("1")
                .code("C")
                .weight(1.0)
                .build();

        medicationList.add(med);
        droneEntityList.add(droneEntity);

        lenient().when(droneRepository.findById(anyString())).thenReturn(Optional.ofNullable(droneEntity));
        lenient().when(droneMapper.toDto(droneEntity)).thenReturn(drone);
        lenient().when(droneRepository.save(any(com.musala.challenge.entities.Drone.class))).thenReturn(droneEntity);
        lenient().when(droneMapper.toEntity(any(Drone.class))).thenReturn(droneEntity);
        when(droneMapper.toDto(any(com.musala.challenge.entities.Drone.class))).thenReturn(drone);
    }

    @Test
    void createDroneTest() {
        droneService.createDrone(drone);
        assertEquals(droneEntity.getBatteryCapacity(), this.drone.getBatteryCapacity());
        assertEquals(droneEntity.getState().toString(), this.drone.getDroneState());
        assertEquals(droneEntity.getModel().toString(), this.drone.getModel());
        assertEquals(droneEntity.getWeightLimit(), this.drone.getWeightLimit());
        assertEquals(droneEntity.getSerialNumber(), this.drone.getNumber());
    }

    @Test
    void loadDroneTest() {
        droneService.loadDrone(drone.getNumber(), medicationList);
        assertEquals(drone.getMedications().size(), medicationList.size());
        assertEquals(drone.getMedications().get(0), medicationList.get(0));
    }

    @Test
    void getDronesForLoadingTest(){
        when(droneRepository.findByState(DroneState.IDLE)).thenReturn(Optional.ofNullable(droneEntityList));
       droneService.getDronesForLoading();
       assertEquals(DroneState.IDLE, droneEntityList.get(0).getState());

    }



}
