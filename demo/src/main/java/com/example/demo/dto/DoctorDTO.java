package com.example.demo.dto;

public class DoctorDTO {

    private String firstName;
    private String lastName;
    private String speciality;
    private String phone;
    private Integer departmentId;  // reference to Department

    // Constructors
    public DoctorDTO() {}

    public DoctorDTO(String firstName, String lastName, String speciality, String phone, Integer departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.phone = phone;
        this.departmentId = departmentId;
    }

    // Getters and setters
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
}
