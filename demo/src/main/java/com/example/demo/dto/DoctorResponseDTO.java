package com.example.demo.dto;

public class DoctorResponseDTO {
    private Integer doctorId;
    private String firstName;
    private String lastName;
    private String speciality;
    private String phone;
    private Integer departmentId;
    private String departmentName;

    public DoctorResponseDTO() {}

    public DoctorResponseDTO(Integer doctorId, String firstName, String lastName, String speciality, String phone,
                             Integer departmentId, String departmentName) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.phone = phone;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // Getters and setters
    public Integer getDoctorId() { return doctorId; }
    public void setDoctorId(Integer doctorId) { this.doctorId = doctorId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getSpeciality() { return speciality; }
    public void setSpeciality(String speciality) { this.speciality = speciality; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Integer getDepartmentId() { return departmentId; }
    public void setDepartmentId(Integer departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
}

