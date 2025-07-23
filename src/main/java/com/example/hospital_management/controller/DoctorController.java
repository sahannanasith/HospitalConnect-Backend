package com.example.hospital_management.controller;

import com.example.hospital_management.dto.DoctorDTO;
import com.example.hospital_management.entity.Doctor;
import com.example.hospital_management.service.impl.DoctorServiceIMPL;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to access
public class DoctorController {

    @Autowired
    private DoctorServiceIMPL doctorService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor savedDoctor = doctorService.saveDoctor(doctorDTO);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return doctorService.getDoctorById(id)
                .map(existingDoctor -> {
                    existingDoctor.setFirstName(doctor.getFirstName());
                    existingDoctor.setLastName(doctor.getLastName());
                    existingDoctor.setSpecialization(doctor.getSpecialization());
                    existingDoctor.setPhoneNumber(doctor.getPhoneNumber());
                    existingDoctor.setEmail(doctor.getEmail());
                    return ResponseEntity.ok(doctorService.saveDoctor(modelMapper.map(existingDoctor, DoctorDTO.class)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        if (doctorService.getDoctorById(id).isPresent()) {
            doctorService.deleteDoctor(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}