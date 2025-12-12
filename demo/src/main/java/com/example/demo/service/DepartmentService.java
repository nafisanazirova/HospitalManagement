package com.example.demo.service;

import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Create or Save
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Read all
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Read by ID
    public Optional<Department> getDepartmentById(Integer id) {
        return departmentRepository.findById(id);
    }

    // Update
    public Optional<Department> updateDepartment(Integer id, Department department) {
        return departmentRepository.findById(id).map(existing -> {
            existing.setName(department.getName());
            existing.setLocation(department.getLocation());
            return departmentRepository.save(existing);
        });
    }

    // Delete
    public boolean deleteDepartment(Integer id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
