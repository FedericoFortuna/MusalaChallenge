package com.musala.challenge.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "drone")
public class Drone {

    @Id
    private String serialNumber;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "weight_limit", nullable = false)
    private String weightLimit;

    @Column(name = "battery_capacity", nullable = false)
    private String batteryCapacity;

    @Column(name = "state", nullable = false)
    private String state;


}
