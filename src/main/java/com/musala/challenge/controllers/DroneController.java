package com.musala.challenge.controllers;

import com.musala.challenge.dtos.Drone;
import com.musala.challenge.dtos.Medication;
import com.musala.challenge.services.IDroneService;
import com.musala.challenge.services.IMedicationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/drone")
public class DroneController {
    @Autowired
    private IDroneService iDroneService;

    @Autowired
    private IMedicationService iMedicationService;

    @PostMapping(value = "/create")
    public ResponseEntity<Drone> createDrone(@RequestBody Drone drone){
        return new ResponseEntity<>(iDroneService.createDrone(drone), HttpStatus.OK);
    }

    @PutMapping(value = "/{serialNumber}/load")
    public ResponseEntity<Drone> loadDroneWithMedications(@PathVariable String serialNumber,
                                                           @RequestBody List<Medication> medications){

        return new ResponseEntity<>(iDroneService.loadDrone(serialNumber, medications), HttpStatus.OK);
    }

    @GetMapping(value = "/{serialNumber}/check")
    public ResponseEntity<List<Medication>> checkMedicationsLoaded(@PathVariable String serialNumber){
        return new ResponseEntity<>(iMedicationService.checkLoadedMedication(serialNumber), HttpStatus.OK);
    }

    @GetMapping(value = "/available")
    public ResponseEntity<List<Drone>> getAvailablesDronesForLoading(){
        return new ResponseEntity<>(iDroneService.getDronesForLoading(), HttpStatus.OK);
    }

    @GetMapping(value = "/{serialNumber}/battery")
    public ResponseEntity<Integer> getDroneBattery(@PathVariable String serialNumber){
        return new ResponseEntity<>(iDroneService.getDroneBattery(serialNumber), HttpStatus.OK);
    }

    @PatchMapping(value = "/{serialNumber}/loading")
    public ResponseEntity<HttpStatus> changeStatusToLoading(@PathVariable String serialNumber){
        iDroneService.changeStatusToLoading(serialNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
