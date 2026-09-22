package com.smartcampus.controller;

import com.smartcampus.model.*;
import com.smartcampus.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final StudentRepository studentRepo;
    private final LecturerRepository lecturerRepo;
    private final CourseRepository courseRepo;
    private final DepartmentRepository deptRepo;
    private final ProgramRepository progRepo;
    private final AcademicYearRepository yearRepo;
    private final SemesterRepository semRepo;
    private final ResultRepository resultRepo;
    private final EnrollmentRepository enrollmentRepo;

    public AdminController(StudentRepository s, LecturerRepository l, CourseRepository c, DepartmentRepository d,
                           ProgramRepository p, AcademicYearRepository y, SemesterRepository sem,
                           ResultRepository r, EnrollmentRepository e) {
        this.studentRepo = s; this.lecturerRepo = l; this.courseRepo = c; this.deptRepo = d;
        this.progRepo = p; this.yearRepo = y; this.semRepo = sem; this.resultRepo = r; this.enrollmentRepo = e;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("studentCount", studentRepo.count());
        model.addAttribute("lecturerCount", lecturerRepo.count());
        model.addAttribute("courseCount", courseRepo.count());
        model.addAttribute("deptCount", deptRepo.count());
        model.addAttribute("students", studentRepo.findAll());
        model.addAttribute("courses", courseRepo.findAll());
        model.addAttribute("departments", deptRepo.findAll());
        model.addAttribute("programs", progRepo.findAll());
        model.addAttribute("enrollments", enrollmentRepo.findAll().size());
        model.addAttribute("results", resultRepo.findAll().size());
        return "admin/dashboard";
    }

    // --- Departments ---
    @GetMapping("/departments")
    public String departments(Model m) { m.addAttribute("departments", deptRepo.findAll()); m.addAttribute("dept", new Department()); return "admin/departments"; }
    @PostMapping("/departments")
    public String saveDept(@ModelAttribute Department dept) { deptRepo.save(dept); return "redirect:/admin/departments"; }

    // --- Programs ---
    @GetMapping("/programs")
    public String programs(Model m) { m.addAttribute("programs", progRepo.findAll()); m.addAttribute("departments", deptRepo.findAll()); m.addAttribute("program", new Program()); return "admin/programs"; }
    @PostMapping("/programs")
    public String saveProg(@ModelAttribute Program program, @RequestParam Long departmentId) {
        Department d = deptRepo.findById(departmentId).orElse(null);
        program.setDepartment(d);
        progRepo.save(program);
        return "redirect:/admin/programs";
    }

    // --- Courses ---
    @GetMapping("/courses")
    public String courses(Model m) { m.addAttribute("courses", courseRepo.findAll()); m.addAttribute("departments", deptRepo.findAll()); m.addAttribute("programs", progRepo.findAll()); m.addAttribute("course", new Course()); return "admin/courses"; }
    @PostMapping("/courses")
    public String saveCourse(@ModelAttribute Course course, @RequestParam Long departmentId, @RequestParam(required=false) Long programId) {
        course.setDepartment(deptRepo.findById(departmentId).orElse(null));
        if (programId != null) course.setProgram(progRepo.findById(programId).orElse(null));
        courseRepo.save(course);
        return "redirect:/admin/courses";
    }

    // --- Students ---
    @GetMapping("/students")
    public String students(Model m) { m.addAttribute("students", studentRepo.findAll()); return "admin/students"; }

    // --- Academic Years ---
    @GetMapping("/years")
    public String years(Model m) { m.addAttribute("years", yearRepo.findAll()); m.addAttribute("year", new AcademicYear()); return "admin/years"; }
    @PostMapping("/years")
    public String saveYear(@ModelAttribute AcademicYear y) { yearRepo.save(y); return "redirect:/admin/years"; }

    // --- Semesters ---
    @GetMapping("/semesters")
    public String semesters(Model m) { m.addAttribute("semesters", semRepo.findAll()); m.addAttribute("years", yearRepo.findAll()); m.addAttribute("semester", new Semester()); return "admin/semesters"; }
    @PostMapping("/semesters")
    public String saveSem(@ModelAttribute Semester sem, @RequestParam Long academicYearId) {
        sem.setAcademicYear(yearRepo.findById(academicYearId).orElse(null));
        semRepo.save(sem);
        return "redirect:/admin/semesters";
    }

    // Reports
    @GetMapping("/reports")
    public String reports(Model m) {
        m.addAttribute("students", studentRepo.findAll());
        m.addAttribute("courses", courseRepo.findAll());
        m.addAttribute("results", resultRepo.findAll());
        return "admin/reports";
    }
}
