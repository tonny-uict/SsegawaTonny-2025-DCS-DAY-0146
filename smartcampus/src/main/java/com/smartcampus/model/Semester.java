package com.smartcampus.model;

import jakarta.persistence.*;

@Entity
public class Semester {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Semester 1 / Semester 2
    private String code;

    @ManyToOne
    private AcademicYear academicYear;
    private boolean current = false;

    public Semester() {}
    public Semester(String name, String code, AcademicYear ay) { this.name = name; this.code = code; this.academicYear = ay; }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String n) { this.name = n; }
    public String getCode() { return code; } public void setCode(String c) { this.code = c; }
    public AcademicYear getAcademicYear() { return academicYear; } public void setAcademicYear(AcademicYear a) { this.academicYear = a; }
    public boolean isCurrent() { return current; } public void setCurrent(boolean c) { this.current = c; }
}
