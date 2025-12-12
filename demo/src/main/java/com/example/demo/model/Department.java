package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Department")
public class Department {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;
    private String name;
    private String location;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Doctor> doctors;

    // Constructors, getters, setters
    public Department() {}
    public Department(int deptId, String name, String location) {
        this.deptId = deptId;
        this.name = name;
        this.location = location;
    }

    public Integer getDeptId() { return deptId; }
    public void setDeptId(Integer deptId) { this.deptId = deptId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public List<Doctor> getDoctors() { return doctors; }
    public void setDoctors(List<Doctor> doctors) { this.doctors = doctors; }
}

