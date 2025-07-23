package com.example.hospital_management.service.impl;

import com.example.hospital_management.dto.PatientDTO;
import com.example.hospital_management.entity.Patient;
import com.example.hospital_management.repository.PatientRepository;
import com.example.hospital_management.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceIMPL implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient savePatient(PatientDTO patientDTO) {
        Patient patient = modelMapper.map(patientDTO, Patient.class);

        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}