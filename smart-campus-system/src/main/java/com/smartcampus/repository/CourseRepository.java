package com.smartcampus.repository;

import com.smartcampus.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByDepartmentId(Long deptId);
    List<Course> findByProgramId(Long programId);
    java.util.Optional<Course> findByCourseCode(String code);
}
