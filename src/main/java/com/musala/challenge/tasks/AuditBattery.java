package com.musala.challenge.tasks;

import com.musala.challenge.conf.LoggingTag;
import com.musala.challenge.dtos.Drone;
import com.musala.challenge.mappers.DroneMapper;
import com.musala.challenge.repositories.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class AuditBattery {


    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AuditBattery.class);
    private final Logger log = LoggerFactory.getLogger(AuditBattery.class);
    private final String LOG_AUDIT_BATTERY_LEVEL = "{} {} - {}: AUDIT BATTERY LEVEL INTO A LOG";
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneMapper droneMapper;

    @Scheduled(cron = "0 */30 * ? * *")
    public void checkDronesBatteryLevelTask() {
        log.info(LOG_AUDIT_BATTERY_LEVEL, LocalDateTime.now(), LoggingTag.AUDIT_BATTERY, AuditBattery.class.getSimpleName());
        this.getDrones().forEach(drone -> logger.info(LoggingTag.AUDIT_BATTERY + " : Battery level for drone " +  drone.getNumber() + " is " + drone.getBatteryCapacity() + "%"));
    }


    private List<Drone> getDrones() {
        List<com.musala.challenge.entities.Drone> droneList = droneRepository.findAll();
        List<Drone> drones = new ArrayList<>();
        droneList.forEach(drone -> drones.add(droneMapper.toDto(drone)));
        return drones;
    }

}
