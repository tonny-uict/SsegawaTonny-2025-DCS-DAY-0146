package com.smartcampus.model;

import jakarta.persistence.*;

@Entity
public class AcademicYear {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String yearLabel; // e.g., 2025/2026
    private boolean current = false;

    public AcademicYear() {}
    public AcademicYear(String label, boolean current) { this.yearLabel = label; this.current = current; }

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getYearLabel() { return yearLabel; } public void setYearLabel(String y) { this.yearLabel = y; }
    public boolean isCurrent() { return current; } public void setCurrent(boolean c) { this.current = c; }
}
