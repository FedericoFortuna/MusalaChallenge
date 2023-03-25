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
    public ResponseEntity<String> createDrone(@RequestBody Drone drone){
        return new ResponseEntity<>(iDroneService.createDrone(drone).toString(), HttpStatus.OK);
    }

    @PutMapping(value = "/{serialNumber}/load")
    public ResponseEntity<String> loadDroneWithMedications(@PathVariable String serialNumber,
                                                           @RequestBody List<Medication> medications){

        return new ResponseEntity<>(iDroneService.loadDrone(serialNumber, medications).toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/{serialNumber}/check")
    public ResponseEntity<String> checkMedicationsLoaded(@PathVariable String serialNumber){
        return new ResponseEntity<>(iMedicationService.checkLoadedMedication(serialNumber).toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/available")
    public ResponseEntity<String> getAvailablesDronesForLoading(){
        return new ResponseEntity<>(iDroneService.getDronesForLoading().toString(), HttpStatus.OK);
    }

}
