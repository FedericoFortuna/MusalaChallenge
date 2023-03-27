package com.musala.challenge.repositories;

import com.musala.challenge.entities.Drone;
import com.musala.challenge.enums.DroneState;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {
    Optional<List<Drone>> findByState(DroneState droneState);

/*    @Override
    @EntityGraph(attributePaths = "medications")
    Optional<Drone> findById(String serialNumber);*/

    @Override
    @EntityGraph(attributePaths = "medications")
    List<Drone> findAll();
}
