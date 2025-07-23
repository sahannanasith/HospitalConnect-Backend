package com.example.hospital_management.controller;

import com.example.hospital_management.dto.AppointmentDTO;
import com.example.hospital_management.entity.Appointment;
import com.example.hospital_management.service.impl.AppointmentServiceIMPL;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to access
public class AppointmentController {

    @Autowired
    private AppointmentServiceIMPL appointmentService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        Appointment savedAppointment = appointmentService.saveAppointment(appointmentDTO);
        return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        return appointmentService.getAppointmentById(id)
                .map(existingAppointment -> {
                    existingAppointment.setAppointmentTime(appointment.getAppointmentTime());
                    existingAppointment.setReason(appointment.getReason());
                    existingAppointment.setStatus(appointment.getStatus());
                    // Note: Patient and Doctor are typically set by ID in the incoming request
                    // and handled by the service layer, or assumed to be unchanged if not provided.
                    // For simplicity, we are not updating patient/doctor foreign keys directly here
                    // if they are not part of the request body for an update.
                    return ResponseEntity.ok(appointmentService.saveAppointment(modelMapper.map(existingAppointment, AppointmentDTO.class)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        if (appointmentService.getAppointmentById(id).isPresent()) {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}