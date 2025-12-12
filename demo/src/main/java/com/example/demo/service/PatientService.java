package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Create or Save
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Read all
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Read by ID
    public Optional<Patient> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }

    // Update
    public Optional<Patient> updatePatient(Integer id, Patient patient) {
        return patientRepository.findById(id).map(existing -> {
            existing.setFirstName(patient.getFirstName());
            existing.setLastName(patient.getLastName());
            existing.setDob(patient.getDob());
            existing.setGender(patient.getGender());
            existing.setPhone(patient.getPhone());
            existing.setAddress(patient.getAddress());
            return patientRepository.save(existing);
        });
    }

    // Delete
    public boolean deletePatient(Integer id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


