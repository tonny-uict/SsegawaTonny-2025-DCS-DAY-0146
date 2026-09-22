package com.smartcampus.model;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String courseCode; // e.g., CSC211, OOP101
    @Column(nullable = false)
    private String courseName;
    private String description;
    private int creditUnits = 3;
    private int semesterOffered = 1; // 1 or 2

    @ManyToOne
    private Department department;

    @ManyToOne
    private Program program;

    private int courseworkWeight = 40; // 40%
    private int examWeight = 60; // 60%

    public Course() {}
    public Course(String code, String name, int creditUnits, Department dept) {
        this.courseCode = code; this.courseName = name; this.creditUnits = creditUnits; this.department = dept;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getCourseCode() { return courseCode; } public void setCourseCode(String c) { this.courseCode = c; }
    public String getCourseName() { return courseName; } public void setCourseName(String n) { this.courseName = n; }
    public String getDescription() { return description; } public void setDescription(String d) { this.description = d; }
    public int getCreditUnits() { return creditUnits; } public void setCreditUnits(int c) { this.creditUnits = c; }
    public int getSemesterOffered() { return semesterOffered; } public void setSemesterOffered(int s) { this.semesterOffered = s; }
    public Department getDepartment() { return department; } public void setDepartment(Department d) { this.department = d; }
    public Program getProgram() { return program; } public void setProgram(Program p) { this.program = p; }
    public int getCourseworkWeight() { return courseworkWeight; } public void setCourseworkWeight(int w) { this.courseworkWeight = w; }
    public int getExamWeight() { return examWeight; } public void setExamWeight(int w) { this.examWeight = w; }
}
