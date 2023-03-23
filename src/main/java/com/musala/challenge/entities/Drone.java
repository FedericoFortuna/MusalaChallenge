package com.musala.challenge.entities;

import com.musala.challenge.enums.DroneModel;
import com.musala.challenge.enums.DroneState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "drone")
@Getter
public class Drone {

    @Id
    private String serialNumber;

    @Column(name = "model", nullable = false)
    private DroneModel model;

    @Column(name = "weight_limit", nullable = false)
    private String weightLimit;

    @Column(name = "battery_capacity", nullable = false)
    private String batteryCapacity;

    @Column(name = "state", nullable = false)
    private DroneState state;


}
