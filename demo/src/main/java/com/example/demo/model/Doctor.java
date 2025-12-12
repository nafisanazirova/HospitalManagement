package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "doctorId")
@Entity
@Table(name = "doctor")
public class Doctor {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;

    private String firstName;
    private String lastName;
    private String speciality;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    //@JsonBackReference   // Prevent recursion back to department.doctors
    private Department department;

    @OneToMany(mappedBy = "doctor")
    @JsonManagedReference  // Optional: if you include appointments
    private List<Appointment> appointments;

    // Constructors, getters, setters
    public Doctor() {}
    public Doctor(int doctorId, String firstName, String lastName, String speciality, String phone) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.phone = phone;
    }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getSpeciality() { return speciality; }
    public void setSpeciality(String speciality) { this.speciality = speciality; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}




