package com.smartcampus.controller;

import com.smartcampus.model.*;
import com.smartcampus.repository.*;
import com.smartcampus.service.AcademicService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final ResultRepository resultRepo;
    private final AttendanceRepository attendanceRepo;
    private final CourseRepository courseRepo;
    private final SemesterRepository semesterRepo;
    private final AcademicYearRepository yearRepo;
    private final AcademicService academicService;

    public StudentController(StudentRepository s, EnrollmentRepository e, ResultRepository r, AttendanceRepository a,
                             CourseRepository c, SemesterRepository sem, AcademicYearRepository y, AcademicService ac) {
        this.studentRepo = s; this.enrollmentRepo = e; this.resultRepo = r; this.attendanceRepo = a;
        this.courseRepo = c; this.semesterRepo = sem; this.yearRepo = y; this.academicService = ac;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model m) {
        Student student = studentRepo.findByUserUsername(auth.getName()).orElse(null);
        if (student == null) { m.addAttribute("error", "Student profile not found"); return "student/dashboard"; }
        List<Enrollment> enrollments = enrollmentRepo.findByStudentId(student.getId());
        List<Result> results = resultRepo.findByStudentId(student.getId());
        List<Attendance> attendances = attendanceRepo.findByStudentId(student.getId());
        double gpa = academicService.calculateGPA(student.getId());
        m.addAttribute("student", student);
        m.addAttribute("enrollments", enrollments);
        m.addAttribute("results", results);
        m.addAttribute("attendances", attendances);
        m.addAttribute("gpa", gpa);
        m.addAttribute("standing", academicService.getAcademicStanding(gpa));
        return "student/dashboard";
    }

    @GetMapping("/courses")
    public String myCourses(Authentication auth, Model m) {
        Student s = studentRepo.findByUserUsername(auth.getName()).orElse(null);
        m.addAttribute("enrollments", s != null ? enrollmentRepo.findByStudentId(s.getId()) : List.of());
        m.addAttribute("availableCourses", courseRepo.findAll());
        return "student/courses";
    }

    @PostMapping("/courses/register")
    public String registerCourse(Authentication auth, @RequestParam Long courseId) {
        Student s = studentRepo.findByUserUsername(auth.getName()).orElse(null);
        Course c = courseRepo.findById(courseId).orElse(null);
        Semester sem = semesterRepo.findByCurrentTrue().orElse(semesterRepo.findAll().stream().findFirst().orElse(null));
        AcademicYear ay = yearRepo.findByCurrentTrue().orElse(yearRepo.findAll().stream().findFirst().orElse(null));
        if (s != null && c != null && sem != null && !enrollmentRepo.existsByStudentIdAndCourseIdAndSemesterId(s.getId(), c.getId(), sem.getId())) {
            enrollmentRepo.save(new Enrollment(s, c, sem, ay));
        }
        return "redirect:/student/courses";
    }

    @GetMapping("/results")
    public String results(Authentication auth, Model m) {
        Student s = studentRepo.findByUserUsername(auth.getName()).orElse(null);
        List<Result> results = s != null ? resultRepo.findByStudentId(s.getId()) : List.of();
        double gpa = s != null ? academicService.calculateGPA(s.getId()) : 0;
        m.addAttribute("results", results);
        m.addAttribute("gpa", gpa);
        m.addAttribute("student", s);
        return "student/results";
    }

    @GetMapping("/transcript")
    public String transcript(Authentication auth, Model m) {
        Student s = studentRepo.findByUserUsername(auth.getName()).orElse(null);
        List<Result> results = s != null ? resultRepo.findByStudentId(s.getId()) : List.of();
        double gpa = s != null ? academicService.calculateGPA(s.getId()) : 0;
        int totalCredits = results.stream().mapToInt(r -> r.getCourse().getCreditUnits()).sum();
        m.addAttribute("student", s);
        m.addAttribute("results", results);
        m.addAttribute("gpa", gpa);
        m.addAttribute("totalCredits", totalCredits);
        m.addAttribute("standing", academicService.getAcademicStanding(gpa));
        return "student/transcript";
    }

    @GetMapping("/attendance")
    public String attendance(Authentication auth, Model m) {
        Student s = studentRepo.findByUserUsername(auth.getName()).orElse(null);
        m.addAttribute("attendances", s != null ? attendanceRepo.findByStudentId(s.getId()) : List.of());
        return "student/attendance";
    }
}
