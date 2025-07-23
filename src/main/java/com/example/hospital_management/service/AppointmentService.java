package com.example.hospital_management.service;

import com.example.hospital_management.dto.AppointmentDTO;
import com.example.hospital_management.entity.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Optional<Appointment> getAppointmentById(Long id);
    Appointment saveAppointment(AppointmentDTO appointmentDTO);
    void deleteAppointment(Long id);
}
