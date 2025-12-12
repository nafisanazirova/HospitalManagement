package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "apptId")
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appt_id")
    private int apptId;

    @Column(name = "appt_date")
    private LocalDate apptDate;

    @Column(name = "appt_time")
    private LocalTime apptTime;

    @Column(name = "appt_status")
    private String apptStatus;

    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // Constructors
    public Appointment() {}

    public Appointment(LocalDate apptDate, LocalTime apptTime, String apptStatus, String reason,
                       Doctor doctor, Patient patient) {
        this.apptDate = apptDate;
        this.apptTime = apptTime;
        this.apptStatus = apptStatus;
        this.reason = reason;
        this.doctor = doctor;
        this.patient = patient;
    }

    // Getters and Setters
    public int getApptId() { return apptId; }
    public void setApptId(int apptId) { this.apptId = apptId; }

    public LocalDate getApptDate() { return apptDate; }
    public void setApptDate(LocalDate apptDate) { this.apptDate = apptDate; }

    public LocalTime getApptTime() { return apptTime; }
    public void setApptTime(LocalTime apptTime) { this.apptTime = apptTime; }

    public String getApptStatus() { return apptStatus; }
    public void setApptStatus(String apptStatus) { this.apptStatus = apptStatus; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
}


