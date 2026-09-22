package com.smartcampus.controller;

import com.smartcampus.repository.*;
import com.smartcampus.service.AcademicService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final StudentRepository studentRepo;
    private final LecturerRepository lecturerRepo;
    private final CourseRepository courseRepo;
    private final DepartmentRepository deptRepo;

    public HomeController(StudentRepository s, LecturerRepository l, CourseRepository c, DepartmentRepository d) {
        this.studentRepo = s; this.lecturerRepo = l; this.courseRepo = c; this.deptRepo = d;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("students", studentRepo.count());
        model.addAttribute("lecturers", lecturerRepo.count());
        model.addAttribute("courses", courseRepo.count());
        model.addAttribute("departments", deptRepo.count());
        return "index";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth) {
        if (auth == null) return "redirect:/login";
        String role = auth.getAuthorities().iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) return "redirect:/admin/dashboard";
        if (role.equals("ROLE_LECTURER")) return "redirect:/lecturer/dashboard";
        if (role.equals("ROLE_STUDENT")) return "redirect:/student/dashboard";
        return "redirect:/";
    }
}
