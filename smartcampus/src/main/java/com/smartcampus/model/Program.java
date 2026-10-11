package com.smartcampus.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Program {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String code;
    private int durationYears = 3;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "program")
    private List<Student> students = new ArrayList<>();

    public Program() {}
    public Program(String name, String code, Department dept) {
        this.name = name; this.code = code; this.department = dept;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getCode() { return code; } public void setCode(String code) { this.code = code; }
    public int getDurationYears() { return durationYears; } public void setDurationYears(int d) { this.durationYears = d; }
    public Department getDepartment() { return department; } public void setDepartment(Department d) { this.department = d; }
}
