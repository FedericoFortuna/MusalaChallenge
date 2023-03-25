package com.musala.challenge.services;

import com.musala.challenge.dtos.Medication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMedicationService {
    List<Medication> checkLoadedMedication(String serialNumberDrone);
}
