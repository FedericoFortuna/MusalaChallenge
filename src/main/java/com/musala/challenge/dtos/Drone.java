package com.musala.challenge.dtos;

import com.musala.challenge.enums.DroneState;
import com.musala.challenge.exceptions.NullFieldException;
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

    private String model;

    private String weightLimit;

    private String batteryCapacity;

    private String droneState;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drone drone = (Drone) o;
        return Objects.equals(number, drone.number) && Objects.equals(model, drone.model) && Objects.equals(weightLimit, drone.weightLimit) && Objects.equals(batteryCapacity, drone.batteryCapacity) && droneState == drone.droneState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, weightLimit, batteryCapacity, droneState);
    }

    public void validate(){
        if(this.getDroneState() == null || this.getModel() == null || this.getBatteryCapacity() == null ||
                this.getNumber() == null || this.getWeightLimit() == null){
            throw new NullFieldException();
        }
    }


}
