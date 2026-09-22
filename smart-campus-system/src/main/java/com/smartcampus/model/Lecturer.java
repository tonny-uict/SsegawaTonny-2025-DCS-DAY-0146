package com.smartcampus.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lecturer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String staffNumber;
    private String specialization;

    @ManyToOne
    private Department department;

    @ManyToMany
    @JoinTable(name = "lecturer_courses",
        joinColumns = @JoinColumn(name = "lecturer_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> assignedCourses = new HashSet<>();

    public Lecturer() {}
    public Lecturer(User user, String staffNumber, Department dept) {
        this.user = user; this.staffNumber = staffNumber; this.department = dept;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public User getUser() { return user; } public void setUser(User u) { this.user = u; }
    public String getStaffNumber() { return staffNumber; } public void setStaffNumber(String s) { this.staffNumber = s; }
    public String getSpecialization() { return specialization; } public void setSpecialization(String s) { this.specialization = s; }
    public Department getDepartment() { return department; } public void setDepartment(Department d) { this.department = d; }
    public Set<Course> getAssignedCourses() { return assignedCourses; } public void setAssignedCourses(Set<Course> c) { this.assignedCourses = c; }
}
