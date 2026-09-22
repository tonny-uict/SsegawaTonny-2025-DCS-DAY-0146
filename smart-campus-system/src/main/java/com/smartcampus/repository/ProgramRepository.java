package com.smartcampus.repository;

import com.smartcampus.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByDepartmentId(Long deptId);
}
