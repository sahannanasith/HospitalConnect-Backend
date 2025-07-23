package com.example.hospital_management.service.impl;

import com.example.hospital_management.dto.AppointmentDTO;
import com.example.hospital_management.entity.Appointment;
import com.example.hospital_management.repository.AppointmentRepository;
import com.example.hospital_management.repository.DoctorRepository;
import com.example.hospital_management.repository.PatientRepository;
import com.example.hospital_management.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceIMPL implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment saveAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);

        // Ensure patient and doctor exist before saving appointment
        if (appointment.getPatient() != null && appointment.getPatient().getId() != null) {
            patientRepository.findById(appointment.getPatient().getId())
                    .ifPresent(appointment::setPatient);
        }
        if (appointment.getDoctor() != null && appointment.getDoctor().getId() != null) {
            doctorRepository.findById(appointment.getDoctor().getId())
                    .ifPresent(appointment::setDoctor);
        }
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
