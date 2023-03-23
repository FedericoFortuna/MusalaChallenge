package com.musala.challenge.services;

import com.musala.challenge.dtos.Drone;
import org.springframework.stereotype.Service;

@Service
public interface IDroneService {
    Drone createDrone(Drone drone);
}
