package com.smartcampus.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id", "semester_id"}))
public class Enrollment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Semester semester;

    @ManyToOne
    private AcademicYear academicYear;

    private LocalDate enrollmentDate = LocalDate.now();
    private String status = "ENROLLED"; // ENROLLED, DROPPED, COMPLETED

    public Enrollment() {}
    public Enrollment(Student s, Course c, Semester sem, AcademicYear ay) {
        this.student = s; this.course = c; this.semester = sem; this.academicYear = ay;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Student getStudent() { return student; } public void setStudent(Student s) { this.student = s; }
    public Course getCourse() { return course; } public void setCourse(Course c) { this.course = c; }
    public Semester getSemester() { return semester; } public void setSemester(Semester s) { this.semester = s; }
    public AcademicYear getAcademicYear() { return academicYear; } public void setAcademicYear(AcademicYear a) { this.academicYear = a; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; } public void setEnrollmentDate(LocalDate d) { this.enrollmentDate = d; }
    public String getStatus() { return status; } public void setStatus(String s) { this.status = s; }
}
