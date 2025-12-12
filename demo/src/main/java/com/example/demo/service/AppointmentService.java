package com.example.demo.service;

import com.example.demo.dto.AppointmentDTO;
import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    // Create from DTO
    public Appointment createFromDTO(AppointmentDTO dto) {
        Appointment appointment = new Appointment();

        // Resolve doctor
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Resolve or create patient
        Patient patient;
        if (dto.getPatientId() != null) {
            patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
        } else {
            patient = new Patient();
            patient.setFirstName(dto.getPatientFirstName());
            patient.setLastName(dto.getPatientLastName());
            patient.setGender(dto.getPatientGender());
            if (dto.getPatientDob() != null) {
                patient.setDob(LocalDate.parse(dto.getPatientDob())); // expects "YYYY-MM-DD"
            }
            patient.setPhone(dto.getPatientPhone());
            patient.setAddress(dto.getPatientAddress());
            patient = patientRepository.save(patient);
        }

        // Fill appointment fields
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setApptDate(LocalDate.parse(dto.getApptDate())); // "YYYY-MM-DD"
        appointment.setApptTime(LocalTime.parse(dto.getApptTime())); // "HH:mm"
        appointment.setReason(dto.getReason());
        appointment.setApptStatus(dto.getApptStatus());

        return appointmentRepository.save(appointment);
    }

    // Update from DTO
    public Optional<Appointment> updateAppointment(Integer id, AppointmentDTO dto) {
        return appointmentRepository.findById(id).map(existing -> {
            Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));

            Patient patient;
            if (dto.getPatientId() != null) {
                patient = patientRepository.findById(dto.getPatientId())
                        .orElseThrow(() -> new RuntimeException("Patient not found"));
            } else {
                patient = new Patient();
                patient.setFirstName(dto.getPatientFirstName());
                patient.setLastName(dto.getPatientLastName());
                patient.setGender(dto.getPatientGender());
                if (dto.getPatientDob() != null) {
                    patient.setDob(LocalDate.parse(dto.getPatientDob()));
                }
                patient.setPhone(dto.getPatientPhone());
                patient.setAddress(dto.getPatientAddress());
                patient = patientRepository.save(patient);
            }

            existing.setDoctor(doctor);
            existing.setPatient(patient);
            existing.setApptDate(LocalDate.parse(dto.getApptDate()));
            existing.setApptTime(LocalTime.parse(dto.getApptTime()));
            existing.setReason(dto.getReason());
            existing.setApptStatus(dto.getApptStatus());

            return appointmentRepository.save(existing);
        });
    }

    // Other methods
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Map<String, Object>> getFullAppointments() {
        return appointmentRepository.getAppointmentsWithNames();
    }

    public Optional<Appointment> getAppointmentById(Integer id) {
        return appointmentRepository.findById(id);
    }

    public boolean deleteAppointment(Integer id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


