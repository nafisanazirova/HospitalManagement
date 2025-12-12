package com.example.demo.controller;

import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.DoctorResponseDTO;
import com.example.demo.model.Department;
import com.example.demo.model.Doctor;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    // Create Doctor
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> createDoctor(@RequestBody DoctorDTO dto) {
        Optional<Department> deptOpt = departmentService.getDepartmentById(dto.getDepartmentId());
        if (deptOpt.isEmpty()) return ResponseEntity.badRequest().build();

        Doctor doctor = new Doctor();
        doctor.setFirstName(dto.getFirstName());
        doctor.setLastName(dto.getLastName());
        doctor.setSpeciality(dto.getSpeciality());
        doctor.setPhone(dto.getPhone());
        doctor.setDepartment(deptOpt.get());

        Doctor saved = doctorService.saveDoctor(doctor);
        return ResponseEntity.ok(toResponseDTO(saved));
    }

    // Get all Doctors
    @GetMapping
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorService.getAllDoctors().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Update Doctor
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@PathVariable Integer id, @RequestBody DoctorDTO dto) {
        Optional<Department> deptOpt = departmentService.getDepartmentById(dto.getDepartmentId());
        if (deptOpt.isEmpty()) return ResponseEntity.badRequest().build();

        Doctor updated = new Doctor();
        updated.setFirstName(dto.getFirstName());
        updated.setLastName(dto.getLastName());
        updated.setSpeciality(dto.getSpeciality());
        updated.setPhone(dto.getPhone());
        updated.setDepartment(deptOpt.get());

        return doctorService.updateDoctor(id, updated)
                .map(d -> ResponseEntity.ok(toResponseDTO(d)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Integer id) {
        boolean deleted = doctorService.deleteDoctor(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Helper
    private DoctorResponseDTO toResponseDTO(Doctor doctor) {
        return new DoctorResponseDTO(
                doctor.getDoctorId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getSpeciality(),
                doctor.getPhone(),
                doctor.getDepartment().getDeptId(),
                doctor.getDepartment().getName()
        );
    }
}


