package com.example.demo.controller;
import java.time.LocalDate;  


import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Create a new patient
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDTO dto) {
        Patient patient = new Patient();
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setPhone(dto.getPhone());
        patient.setGender(dto.getGender());
        patient.setAddress(dto.getAddress());
        patient.setDob(LocalDate.parse(dto.getDob())); // convert String to LocalDate

        Patient saved = patientService.savePatient(patient);
        return ResponseEntity.ok(saved);
    }

    // Get all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // Get patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Integer id) {
        return patientService.getPatientById(id)
                             .map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update patient by ID
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody PatientDTO dto) {
        Patient patient = new Patient();
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setPhone(dto.getPhone());
        patient.setGender(dto.getGender());
        patient.setAddress(dto.getAddress());
        patient.setDob(LocalDate.parse(dto.getDob()));

        return patientService.updatePatient(id, patient)
                             .map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete patient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        boolean deleted = patientService.deletePatient(id); // Update service to return boolean
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }
}

