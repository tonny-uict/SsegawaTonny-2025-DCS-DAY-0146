package com.smartcampus.repository;

import com.smartcampus.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRegistrationNumber(String regNo);
    Optional<Student> findByUserUsername(String username);
    long countByStatus(String status);
}
