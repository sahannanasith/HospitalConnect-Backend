package com.example.hospital_management.service;

import com.example.hospital_management.dto.DoctorDTO;
import com.example.hospital_management.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> getAllDoctors();

    Optional<Doctor> getDoctorById(Long id);

    Doctor saveDoctor(DoctorDTO doctorDTO);

    void deleteDoctor(Long id);
}
