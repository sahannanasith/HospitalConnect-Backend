package com.example.hospital_management.service;

import com.example.hospital_management.dto.PatientDTO;
import com.example.hospital_management.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> getAllPatients();

    Optional<Patient> getPatientById(Long id);

    Patient savePatient(PatientDTO patientDTO);

    void deletePatient(Long id);
}
