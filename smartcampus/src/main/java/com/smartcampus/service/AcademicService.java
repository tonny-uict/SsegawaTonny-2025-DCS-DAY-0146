package com.smartcampus.service;

import com.smartcampus.model.*;
import com.smartcampus.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AcademicService {

    private final ResultRepository resultRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    public AcademicService(ResultRepository resultRepository, EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.resultRepository = resultRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    // GPA = sum(gradePoint * creditUnits) / sum(creditUnits)
    public double calculateGPA(Long studentId) {
        List<Result> results = resultRepository.findByStudentId(studentId);
        if (results.isEmpty()) return 0.0;
        double totalPoints = 0;
        int totalCredits = 0;
        for (Result r : results) {
            if (r.getGradePoint() != null && r.getCourse() != null) {
                totalPoints += r.getGradePoint() * r.getCourse().getCreditUnits();
                totalCredits += r.getCourse().getCreditUnits();
            }
        }
        return totalCredits == 0 ? 0.0 : Math.round((totalPoints / totalCredits) * 100.0) / 100.0;
    }

    public double calculateCGPA(Long studentId, Long semesterId) {
        List<Result> results = resultRepository.findByStudentIdAndSemesterId(studentId, semesterId);
        if (results.isEmpty()) return 0.0;
        double totalPoints = 0;
        int totalCredits = 0;
        for (Result r : results) {
            totalPoints += r.getGradePoint() * r.getCourse().getCreditUnits();
            totalCredits += r.getCourse().getCreditUnits();
        }
        return totalCredits == 0 ? 0.0 : Math.round((totalPoints / totalCredits) * 100.0) / 100.0;
    }

    public String getAcademicStanding(double gpa) {
        if (gpa >= 4.4) return "First Class";
        if (gpa >= 3.6) return "Second Class Upper";
        if (gpa >= 2.8) return "Second Class Lower";
        if (gpa >= 2.0) return "Pass";
        return "Retake / Discontinued";
    }

    public double getAttendancePercentage(Long studentId, Long courseId) {
        // Simplified: present / total * 100
        // In real impl, query attendance repo
        return 0;
    }
}
