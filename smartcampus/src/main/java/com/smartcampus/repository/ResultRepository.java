package com.smartcampus.repository;

import com.smartcampus.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudentId(Long studentId);
    List<Result> findByCourseId(Long courseId);
    Optional<Result> findByStudentIdAndCourseIdAndSemesterId(Long sId, Long cId, Long semId);
    List<Result> findByStudentIdAndSemesterId(Long sId, Long semId);
}
