package com.smartcampus.controller;

import com.smartcampus.model.*;
import com.smartcampus.repository.*;
import com.smartcampus.service.AcademicService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    private final LecturerRepository lecturerRepo;
    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final AttendanceRepository attendanceRepo;
    private final ResultRepository resultRepo;
    private final StudentRepository studentRepo;
    private final SemesterRepository semesterRepo;
    private final AcademicYearRepository yearRepo;

    public LecturerController(LecturerRepository l, CourseRepository c, EnrollmentRepository e, AttendanceRepository a,
                              ResultRepository r, StudentRepository s, SemesterRepository sem, AcademicYearRepository y) {
        this.lecturerRepo = l; this.courseRepo = c; this.enrollmentRepo = e; this.attendanceRepo = a;
        this.resultRepo = r; this.studentRepo = s; this.semesterRepo = sem; this.yearRepo = y;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth, Model m) {
        Optional<Lecturer> lec = lecturerRepo.findByUserUsername(auth.getName());
        m.addAttribute("lecturer", lec.orElse(null));
        m.addAttribute("courses", lec.map(Lecturer::getAssignedCourses).orElse(java.util.Collections.emptySet()));
        m.addAttribute("totalStudents", studentRepo.count());
        return "lecturer/dashboard";
    }

    @GetMapping("/courses")
    public String myCourses(Authentication auth, Model m) {
        Lecturer lec = lecturerRepo.findByUserUsername(auth.getName()).orElse(null);
        m.addAttribute("courses", lec != null ? lec.getAssignedCourses() : courseRepo.findAll());
        return "lecturer/courses";
    }

    @GetMapping("/course/{id}/students")
    public String courseStudents(@PathVariable Long id, Model m) {
        Course c = courseRepo.findById(id).orElse(null);
        m.addAttribute("course", c);
        m.addAttribute("enrollments", enrollmentRepo.findByCourseId(id));
        return "lecturer/course-students";
    }

    // Attendance
    @GetMapping("/attendance")
    public String attendanceForm(Model m) {
        m.addAttribute("courses", courseRepo.findAll());
        m.addAttribute("students", studentRepo.findAll());
        m.addAttribute("attendance", new Attendance());
        m.addAttribute("records", attendanceRepo.findAll());
        return "lecturer/attendance";
    }
    @PostMapping("/attendance")
    public String saveAttendance(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam String status) {
        Student s = studentRepo.findById(studentId).orElse(null);
        Course c = courseRepo.findById(courseId).orElse(null);
        Attendance a = new Attendance(s, c, LocalDate.now(), status);
        attendanceRepo.save(a);
        return "redirect:/lecturer/attendance";
    }

    // Results entry
    @GetMapping("/results")
    public String resultsForm(Model m) {
        m.addAttribute("courses", courseRepo.findAll());
        m.addAttribute("students", studentRepo.findAll());
        m.addAttribute("semesters", semesterRepo.findAll());
        m.addAttribute("results", resultRepo.findAll());
        return "lecturer/results";
    }
    @PostMapping("/results")
    public String saveResult(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam Double coursework, @RequestParam Double exam) {
        Student s = studentRepo.findById(studentId).orElse(null);
        Course c = courseRepo.findById(courseId).orElse(null);
        Semester sem = semesterRepo.findByCurrentTrue().orElse(semesterRepo.findAll().stream().findFirst().orElse(null));
        AcademicYear ay = yearRepo.findByCurrentTrue().orElse(yearRepo.findAll().stream().findFirst().orElse(null));
        // find existing or new
        Result r = resultRepo.findByStudentIdAndCourseIdAndSemesterId(studentId, courseId, sem != null ? sem.getId() : 1L).orElse(new Result());
        r.setStudent(s); r.setCourse(c); r.setSemester(sem); r.setAcademicYear(ay);
        r.setCourseworkMarks(coursework); r.setExamMarks(exam);
        r.calculate();
        resultRepo.save(r);
        return "redirect:/lecturer/results";
    }

    @GetMapping("/reports")
    public String reports(Model m) {
        m.addAttribute("results", resultRepo.findAll());
        return "lecturer/reports";
    }
}
