package com.musala.challenge.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.challenge.enums.DroneModel;
import com.musala.challenge.enums.DroneState;
import com.musala.challenge.exceptions.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Drone {

    @Size(min = 1, message = "The serial number must have at least 1 character")
    @Size(max = 100, message = "The serial number must have as maximum 100 characters")
    private String number;

    private String model;

    private Double weightLimit;

    private Integer batteryCapacity;

    private String droneState;

    private List<Medication> medications = new ArrayList<>();

    public static final Double WEIGHT_LIMIT = 500.0;

    private static final Integer MIN_BATTERY_CAPACITY = 0;
    private static final Integer MAX_BATTERY_CAPACITY = 100;

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

    public void validate() {
        if (this.getDroneState() == null || this.getModel() == null || this.getBatteryCapacity() == null ||
                this.getNumber() == null || this.getWeightLimit() == null) {
            throw new NullFieldException();
        }

        if (!isValidDroneState(this.getDroneState())) {
            throw new DroneStateNotFoundException();
        }

        if (!isValidDroneModel(this.getModel())) {
            throw new DroneModelNotFoundException();
        }

        if (this.getWeightLimit() > WEIGHT_LIMIT) {
            throw new WeightLimitExceededException();
        }

        if (this.getBatteryCapacity() < MIN_BATTERY_CAPACITY || this.getBatteryCapacity() > MAX_BATTERY_CAPACITY) {
            throw new BatteryCapacityException();
        }
    }

    public Drone loadDrone(List<Medication> medications) {
        Double weightMedicationsOnDrone = getWeightMedications();

        for (Medication med : medications) {
            if (weightMedicationsOnDrone + med.getWeight() > WEIGHT_LIMIT) {
                throw new TotalWeightExceededException();
            }
            this.medications.add(med);
            weightMedicationsOnDrone += med.getWeight();
        }


        return this;
    }


    private boolean isValidDroneState(String state) {
        for (DroneState droneState : DroneState.values()) {
            if (droneState.name().equalsIgnoreCase(state)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidDroneModel(String model) {
        for (DroneModel droneModel : DroneModel.values()) {
            if (droneModel.name().equalsIgnoreCase(model)) {
                return true;
            }
        }
        return false;
    }

    private Double getWeightMedications() {
        Double weightMedications;
        if (this.medications == null) {
            weightMedications = 0.0;
        } else {
            weightMedications = this.getMedications().stream()
                    .map(Medication::getWeight)
                    .reduce(0.0, Double::sum);
        }
        return weightMedications;
    }


    public void changeStatusToLoaded(){
        this.droneState = DroneState.LOADED.toString();
    }


}
