package com.smartcampus.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @Column(unique = true)
    private String registrationNumber; // e.g., DCS-DAY-0146
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String phone;
    private LocalDate enrollmentDate = LocalDate.now();

    @ManyToOne
    private Program program;

    @ManyToOne
    private Department department;

    private String status = "ACTIVE"; // ACTIVE, SUSPENDED, GRADUATED

    public Student() {}
    public Student(User user, String regNo, String fn, String ln, Program prog) {
        this.user = user; this.registrationNumber = regNo; this.firstName = fn; this.lastName = ln; this.program = prog;
        if (prog != null) this.department = prog.getDepartment();
    }

    public String getFullName() { return firstName + " " + lastName; }

    // getters/setters
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public User getUser() { return user; } public void setUser(User u) { this.user = u; }
    public String getRegistrationNumber() { return registrationNumber; } public void setRegistrationNumber(String r) { this.registrationNumber = r; }
    public String getFirstName() { return firstName; } public void setFirstName(String f) { this.firstName = f; }
    public String getLastName() { return lastName; } public void setLastName(String l) { this.lastName = l; }
    public LocalDate getDateOfBirth() { return dateOfBirth; } public void setDateOfBirth(LocalDate d) { this.dateOfBirth = d; }
    public String getGender() { return gender; } public void setGender(String g) { this.gender = g; }
    public String getPhone() { return phone; } public void setPhone(String p) { this.phone = p; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; } public void setEnrollmentDate(LocalDate d) { this.enrollmentDate = d; }
    public Program getProgram() { return program; } public void setProgram(Program p) { this.program = p; }
    public Department getDepartment() { return department; } public void setDepartment(Department d) { this.department = d; }
    public String getStatus() { return status; } public void setStatus(String s) { this.status = s; }
}
