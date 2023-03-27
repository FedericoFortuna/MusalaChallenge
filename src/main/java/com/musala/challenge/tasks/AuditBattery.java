package com.musala.challenge.tasks;

import com.musala.challenge.conf.LoggingTag;
import com.musala.challenge.dtos.Drone;
import com.musala.challenge.mappers.DroneMapper;
import com.musala.challenge.repositories.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AuditBattery {


    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AuditBattery.class);
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneMapper droneMapper;

    @Scheduled(cron = "0 0 12 * * ?")
    public void checkDronesBatteryLevelTask() {
        this.getDrones().forEach(drone -> logger.info(LoggingTag.AUDIT_BATTERY + " : Battery level for drone " +  drone.getNumber() + " is " + drone.getBatteryCapacity() + "%"));
    }


    private List<Drone> getDrones() {
        List<com.musala.challenge.entities.Drone> droneList = droneRepository.findAll();
        List<Drone> drones = new ArrayList<>();
        droneList.forEach(drone -> drones.add(droneMapper.toDto(drone)));
        return drones;
    }

}
