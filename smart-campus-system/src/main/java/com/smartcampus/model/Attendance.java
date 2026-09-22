package com.smartcampus.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Attendance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    private LocalDate date;
    private String status; // PRESENT, ABSENT, LATE
    private String week; // Week 1, etc.

    @ManyToOne
    private Semester semester;

    public Attendance() {}
    public Attendance(Student s, Course c, LocalDate date, String status) {
        this.student = s; this.course = c; this.date = date; this.status = status;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Student getStudent() { return student; } public void setStudent(Student s) { this.student = s; }
    public Course getCourse() { return course; } public void setCourse(Course c) { this.course = c; }
    public LocalDate getDate() { return date; } public void setDate(LocalDate d) { this.date = d; }
    public String getStatus() { return status; } public void setStatus(String s) { this.status = s; }
    public String getWeek() { return week; } public void setWeek(String w) { this.week = w; }
    public Semester getSemester() { return semester; } public void setSemester(Semester s) { this.semester = s; }
}
