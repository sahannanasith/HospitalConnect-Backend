package com.example.hospital_management.dto;

import com.example.hospital_management.entity.Doctor;
import com.example.hospital_management.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO {
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentTime;
    private String reason;
    private String status;
}
