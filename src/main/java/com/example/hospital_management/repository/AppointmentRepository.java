package com.example.hospital_management.repository;

import com.example.hospital_management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}