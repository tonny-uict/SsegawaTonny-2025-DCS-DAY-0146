package com.smartcampus.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id", "semester_id"}))
public class Result {
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

    private Double courseworkMarks; // out of 40
    private Double examMarks; // out of 60
    private Double totalMarks; // auto calculated
    private String grade; // A, B, C, D, F
    private Double gradePoint; // 5.0, 4.0 etc
    private String remarks; // PASSED, FAILED, RETAKE

    public Result() {}

    // Auto-calculate on set
    public void calculate() {
        if (courseworkMarks == null) courseworkMarks = 0.0;
        if (examMarks == null) examMarks = 0.0;
        this.totalMarks = courseworkMarks + examMarks;
        // Ugandan grading: >=80 A(5), 70-79 B(4), 60-69 C(3), 50-59 D(2), <50 F(0)
        if (totalMarks >= 80) { grade = "A"; gradePoint = 5.0; remarks = "PASSED"; }
        else if (totalMarks >= 70) { grade = "B"; gradePoint = 4.0; remarks = "PASSED"; }
        else if (totalMarks >= 60) { grade = "C"; gradePoint = 3.0; remarks = "PASSED"; }
        else if (totalMarks >= 50) { grade = "D"; gradePoint = 2.0; remarks = "PASSED"; }
        else { grade = "F"; gradePoint = 0.0; remarks = "FAILED - RETAKE"; }
    }

    // getters/setters with auto recalc
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Student getStudent() { return student; } public void setStudent(Student s) { this.student = s; }
    public Course getCourse() { return course; } public void setCourse(Course c) { this.course = c; }
    public Semester getSemester() { return semester; } public void setSemester(Semester s) { this.semester = s; }
    public AcademicYear getAcademicYear() { return academicYear; } public void setAcademicYear(AcademicYear a) { this.academicYear = a; }
    public Double getCourseworkMarks() { return courseworkMarks; } public void setCourseworkMarks(Double m) { this.courseworkMarks = m; }
    public Double getExamMarks() { return examMarks; } public void setExamMarks(Double m) { this.examMarks = m; }
    public Double getTotalMarks() { return totalMarks; } public void setTotalMarks(Double t) { this.totalMarks = t; }
    public String getGrade() { return grade; } public void setGrade(String g) { this.grade = g; }
    public Double getGradePoint() { return gradePoint; } public void setGradePoint(Double gp) { this.gradePoint = gp; }
    public String getRemarks() { return remarks; } public void setRemarks(String r) { this.remarks = r; }
}
