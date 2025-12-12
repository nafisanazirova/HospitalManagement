package com.example.demo.dto;

public class AppointmentDTO {

    private Integer doctorId;
    private Integer patientId;
    private String apptDate;   // e.g., "2025-12-01"
    private String apptTime;   // e.g., "14:30"
    private String reason;
    private String apptStatus;

    // New patient fields (optional if patientId is null)
    private String patientFirstName;
    private String patientLastName;
    private String patientGender;
    private String patientDob;     // keep as String, parse later
    private String patientPhone;
    private String patientAddress;

    // Getters and setters
    public Integer getDoctorId() { return doctorId; }
    public void setDoctorId(Integer doctorId) { this.doctorId = doctorId; }

    public Integer getPatientId() { return patientId; }
    public void setPatientId(Integer patientId) { this.patientId = patientId; }

    public String getApptDate() { return apptDate; }
    public void setApptDate(String apptDate) { this.apptDate = apptDate; }

    public String getApptTime() { return apptTime; }
    public void setApptTime(String apptTime) { this.apptTime = apptTime; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getApptStatus() { return apptStatus; }
    public void setApptStatus(String apptStatus) { this.apptStatus = apptStatus; }

    public String getPatientFirstName() { return patientFirstName; }
    public void setPatientFirstName(String patientFirstName) { this.patientFirstName = patientFirstName; }

    public String getPatientLastName() { return patientLastName; }
    public void setPatientLastName(String patientLastName) { this.patientLastName = patientLastName; }

    public String getPatientGender() { return patientGender; }
    public void setPatientGender(String patientGender) { this.patientGender = patientGender; }

    public String getPatientDob() { return patientDob; }
    public void setPatientDob(String patientDob) { this.patientDob = patientDob; }

    public String getPatientPhone() { return patientPhone; }
    public void setPatientPhone(String patientPhone) { this.patientPhone = patientPhone; }

    public String getPatientAddress() { return patientAddress; }
    public void setPatientAddress(String patientAddress) { this.patientAddress = patientAddress; }
}
