# SmartCampus — Student Management System
Backend system for academic records. Student, lecturer, course, enrollment, attendance and results management.

Stack: Java 17, Spring Boot 3.3.5, MySQL, Spring Security, Maven.

## Modules
- model: Student, Lecturer, Course, Enrollment, Attendance, Result, User, Program, Department, Semester, AcademicYear
- repository: Spring Data JPA repositories
- service: AcademicService, user details service
- controller, dto, config, security

## Run
```
mvn spring-boot:run
```
Configure MySQL datasource in application properties before running.
