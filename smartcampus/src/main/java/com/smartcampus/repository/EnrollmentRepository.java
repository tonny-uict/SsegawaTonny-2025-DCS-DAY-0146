package com.smartcampus.repository;

import com.smartcampus.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
    List<Enrollment> findBySemesterId(Long semesterId);
    long countByStudentId(Long studentId);
    boolean existsByStudentIdAndCourseIdAndSemesterId(Long sId, Long cId, Long semId);
}
