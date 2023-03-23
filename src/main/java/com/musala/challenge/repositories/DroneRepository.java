package com.musala.challenge.repositories;

import com.musala.challenge.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, String> {

}
