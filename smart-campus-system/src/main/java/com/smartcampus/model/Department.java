package com.smartcampus.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String code;
    private String headOfDepartment;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Program> programs = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    private List<Lecturer> lecturers = new ArrayList<>();

    public Department() {}
    public Department(String name, String code, String head) {
        this.name = name; this.code = code; this.headOfDepartment = head;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getCode() { return code; } public void setCode(String code) { this.code = code; }
    public String getHeadOfDepartment() { return headOfDepartment; } public void setHeadOfDepartment(String h) { this.headOfDepartment = h; }
    public List<Program> getPrograms() { return programs; } public void setPrograms(List<Program> p) { this.programs = p; }
}
