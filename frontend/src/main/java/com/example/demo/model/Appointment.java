package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int apptId;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private LocalDate apptDate;
    private LocalTime apptTime;
    private String reason;
    private String apptStatus;

    // Constructors
    public Appointment() {}

    public Appointment(int apptId, Patient patient, Doctor doctor, LocalDate apptDate,
                       LocalTime apptTime, String reason, String apptStatus) {
        this.apptId = apptId;
        this.patient = patient;
        this.doctor = doctor;
        this.apptDate = apptDate;
        this.apptTime = apptTime;
        this.reason = reason;
        this.apptStatus = apptStatus;
    }

    // Getters and Setters
    public int getApptId() { return apptId; }
    public void setApptId(int apptId) { this.apptId = apptId; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public LocalDate getApptDate() { return apptDate; }
    public void setApptDate(LocalDate apptDate) { this.apptDate = apptDate; }

    public LocalTime getApptTime() { return apptTime; }
    public void setApptTime(LocalTime apptTime) { this.apptTime = apptTime; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getApptStatus() { return apptStatus; }
    public void setApptStatus(String apptStatus) { this.apptStatus = apptStatus; }
}
