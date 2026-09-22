package com.smartcampus.controller;

import com.smartcampus.model.*;
import com.smartcampus.repository.*;
import com.smartcampus.service.AcademicService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final ResultRepository resultRepo;
    private final AcademicService academicService;

    public ApiController(StudentRepository s, CourseRepository c, ResultRepository r, AcademicService a) {
        this.studentRepo = s; this.courseRepo = c; this.resultRepo = r; this.academicService = a;
    }

    @GetMapping("/students")
    public List<Student> allStudents() { return studentRepo.findAll(); }

    @GetMapping("/courses")
    public List<Course> allCourses() { return courseRepo.findAll(); }

    @GetMapping("/student/{id}/gpa")
    public Map<String, Object> gpa(@PathVariable Long id) {
        double gpa = academicService.calculateGPA(id);
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", id);
        map.put("gpa", gpa);
        map.put("standing", academicService.getAcademicStanding(gpa));
        map.put("results", resultRepo.findByStudentId(id));
        return map;
    }

    @GetMapping("/health")
    public Map<String,String> health() { return Map.of("status","UP","app","Smart Campus System"); }
}
