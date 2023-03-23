package com.musala.challenge.entities;

import com.musala.challenge.enums.DroneModel;
import com.musala.challenge.enums.DroneState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private Double weightLimit;

    @Column(name = "battery_capacity", nullable = false)
    private Integer batteryCapacity;

    @Column(name = "state", nullable = false)
    private DroneState state;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    private List<Medication> medications = new ArrayList<>();


}
