package com.smartcampus.config;

import com.smartcampus.model.*;
import com.smartcampus.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            UserRepository userRepo,
            DepartmentRepository deptRepo,
            ProgramRepository progRepo,
            AcademicYearRepository yearRepo,
            SemesterRepository semRepo,
            StudentRepository studentRepo,
            LecturerRepository lecturerRepo,
            CourseRepository courseRepo,
            EnrollmentRepository enrollmentRepo,
            ResultRepository resultRepo,
            PasswordEncoder encoder) {
        return args -> {
            if (userRepo.count() > 0) return; // already initialized

            // Users
            User admin = new User("admin", encoder.encode("admin123"), Role.ADMIN, "System Administrator", "admin@campus.ac.ug");
            User lecUser = new User("lecturer1", encoder.encode("lecturer123"), Role.LECTURER, "Dr. Jane Nakato", "jane@campus.ac.ug");
            User stuUser = new User("student1", encoder.encode("student123"), Role.STUDENT, "Ssegawa Tonny", "tonny@student.campus.ac.ug");
            User stuUser2 = new User("student2", encoder.encode("student123"), Role.STUDENT, "Amina Nalwoga", "amina@student.campus.ac.ug");
            userRepo.save(admin); userRepo.save(lecUser); userRepo.save(stuUser); userRepo.save(stuUser2);

            // Department & Program
            Department dept = new Department("Computer Science", "DCS", "Dr. M. Robert");
            deptRepo.save(dept);
            Department dept2 = new Department("Information Systems", "DIS", "Dr. Kato");
            deptRepo.save(dept2);

            Program prog = new Program("Diploma in Computer Science", "DCS", dept);
            progRepo.save(prog);
            Program prog2 = new Program("Diploma in Information Technology", "DIT", dept);
            progRepo.save(prog2);

            // Academic Year / Semester
            AcademicYear ay = new AcademicYear("2025/2026", true);
            yearRepo.save(ay);
            Semester sem1 = new Semester("Semester 1", "SEM1", ay);
            sem1.setCurrent(true);
            semRepo.save(sem1);
            Semester sem2 = new Semester("Semester 2", "SEM2", ay);
            semRepo.save(sem2);

            // Lecturer
            Lecturer lec = new Lecturer(lecUser, "LEC001", dept);
            lec.setSpecialization("Object Oriented Programming, Java");
            lecturerRepo.save(lec);

            // Students
            Student s1 = new Student(stuUser, "2025/DCS/DAY/0146", "Ssegawa", "Tonny", prog);
            s1.setGender("Male"); s1.setPhone("0700123456"); s1.setDateOfBirth(LocalDate.of(2002,5,10));
            studentRepo.save(s1);
            Student s2 = new Student(stuUser2, "2025/DCS/DAY/0147", "Amina", "Nalwoga", prog);
            s2.setGender("Female"); s2.setPhone("0700654321");
            studentRepo.save(s2);

            // Courses
            Course c1 = new Course("CSC211", "Object Oriented Programming with Java", 4, dept);
            c1.setDescription("Core OOP concepts, inheritance, polymorphism, Spring Boot"); c1.setSemesterOffered(1); c1.setProgram(prog);
            Course c2 = new Course("CSC212", "Data Communication & Networking", 3, dept);
            c2.setDescription("Network devices, OSI, TCP/IP"); c2.setProgram(prog);
            Course c3 = new Course("CSC213", "Database Systems (MySQL)", 4, dept);
            Course c4 = new Course("MGT211", "Management Information Systems", 3, dept2);
            Course c5 = new Course("CSC214", "Systems Project", 3, dept);
            courseRepo.save(c1); courseRepo.save(c2); courseRepo.save(c3); courseRepo.save(c4); courseRepo.save(c5);

            // Assign lecturer to courses
            lec.getAssignedCourses().add(c1); lec.getAssignedCourses().add(c3);
            lecturerRepo.save(lec);

            // Enrollments
            for (Course c : courseRepo.findAll()) {
                if (!enrollmentRepo.existsByStudentIdAndCourseIdAndSemesterId(s1.getId(), c.getId(), sem1.getId())) {
                    enrollmentRepo.save(new Enrollment(s1, c, sem1, ay));
                }
                enrollmentRepo.save(new Enrollment(s2, c, sem1, ay));
            }

            // Sample Results (for s1)
            Result r1 = new Result(); r1.setStudent(s1); r1.setCourse(c1); r1.setSemester(sem1); r1.setAcademicYear(ay); r1.setCourseworkMarks(32.0); r1.setExamMarks(48.0); r1.calculate();
            Result r2 = new Result(); r2.setStudent(s1); r2.setCourse(c2); r2.setSemester(sem1); r2.setAcademicYear(ay); r2.setCourseworkMarks(28.0); r2.setExamMarks(40.0); r2.calculate();
            Result r3 = new Result(); r3.setStudent(s1); r3.setCourse(c3); r3.setSemester(sem1); r3.setAcademicYear(ay); r3.setCourseworkMarks(35.0); r3.setExamMarks(50.0); r3.calculate();
            resultRepo.save(r1); resultRepo.save(r2); resultRepo.save(r3);

            System.out.println("=== Sample Data Initialized ===");
        };
    }
}
