package com.musala.challenge.controllers;

import com.musala.challenge.dtos.Drone;
import com.musala.challenge.services.IDroneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/drone")
public class DroneController {
    @Autowired
    private IDroneService iDroneService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createDrone(@RequestBody Drone drone){
        return new ResponseEntity<>(iDroneService.createDrone(drone).toString(), HttpStatus.OK);
    }

}
