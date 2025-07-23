package com.example.hospital_management.controller;

import com.example.hospital_management.dto.PatientDTO;
import com.example.hospital_management.entity.Patient;
import com.example.hospital_management.service.impl.PatientServiceIMPL;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to access
public class PatientController {

    @Autowired
    private PatientServiceIMPL patientService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDTO patientDTO) {
        Patient savedPatient = patientService.savePatient(patientDTO);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        return patientService.getPatientById(id)
                .map(existingPatient -> {
                    existingPatient.setFirstName(patientDTO.getFirstName());
                    existingPatient.setLastName(patientDTO.getLastName());
                    existingPatient.setDateOfBirth(patientDTO.getDateOfBirth());
                    existingPatient.setGender(patientDTO.getGender());
                    existingPatient.setPhoneNumber(patientDTO.getPhoneNumber());
                    existingPatient.setAddress(patientDTO.getAddress());
                    return ResponseEntity.ok(patientService.savePatient(modelMapper.map(existingPatient, PatientDTO.class)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (patientService.getPatientById(id).isPresent()) {
            patientService.deletePatient(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}