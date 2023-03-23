package com.musala.challenge.dtos;

import com.musala.challenge.enums.DroneModel;
import com.musala.challenge.enums.DroneState;
import lombok.*;

import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Drone {

    private String number;
    private DroneModel model;
    private String weightLimit;
    private String batteryCapacity;
    private DroneState droneState;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return Objects.equals(number, drone.number) && model == drone.model && Objects.equals(weightLimit, drone.weightLimit) && Objects.equals(batteryCapacity, drone.batteryCapacity) && droneState == drone.droneState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, weightLimit, batteryCapacity, droneState);
    }


}
