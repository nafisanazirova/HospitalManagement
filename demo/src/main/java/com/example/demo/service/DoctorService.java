package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Create or Save
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Read all
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Read by ID
    public Optional<Doctor> getDoctorById(Integer id) {
        return doctorRepository.findById(id);
    }

    // Update
    public Optional<Doctor> updateDoctor(Integer id, Doctor doctor) {
        return doctorRepository.findById(id).map(existing -> {
            existing.setFirstName(doctor.getFirstName());
            existing.setLastName(doctor.getLastName());
            existing.setSpeciality(doctor.getSpeciality());
            existing.setPhone(doctor.getPhone());
            existing.setDepartment(doctor.getDepartment());
            return doctorRepository.save(existing);
        });
    }


    // Delete
    public boolean deleteDoctor(Integer id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


