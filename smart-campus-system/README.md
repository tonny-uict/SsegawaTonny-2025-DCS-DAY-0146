# Smart Campus Student Management and Academic Information System

**Course:** Diploma in Computer Science (DCS) - Year 1 Semester 2 - Object Oriented Programming with Java  
**Project Type:** Coursework Project (Full Stack Java)  
**Source Document:** `Desktop/cs OOP.pdf` - Smart Campus System  
**Deadline:** 25 Sept 2026 - eCampus (GitHub Link submission)  
**Location:** `/home/bravehart/DCSYear1Sem2/oop/`  
**Tech Stack:** Java 17/25, Spring Boot 3.3.5, Spring Security, Spring Data JPA (Hibernate), Thymeleaf, Bootstrap 5, H2 (dev) / MySQL (prod), Maven

---

## 1. Background
Universities manage large amounts of student information - registration, courses, attendance, fees, lecturers, departments, results. Manual/disconnected systems cause duplication, delays, inaccurate results and poor reporting. This system provides a **centralized Java-based platform** with role-based access for Admin, Lecturer, Student.

## 2. Objectives Covered (14/14)

| # | Objective | Implementation |
|---|-----------|----------------|
| 1 | Register & manage student information | `Student.java` + `StudentRepository` + Admin UI |
| 2 | Manage academic programs & departments | `Department.java`, `Program.java`, Admin CRUD |
| 3 | Manage courses & course units | `Course.java` (creditUnits, weights), Admin/Courses |
| 4 | Assign lecturers to courses | `Lecturer.assignedCourses` (M2M), Lecturer Dashboard |
| 5 | Register students for courses | `Enrollment.java`, Student/Courses register |
| 6 | Record student attendance | `Attendance.java` (PRESENT/ABSENT/LATE), Lecturer/Attendance |
| 7 | Record coursework & exam marks | `Result.courseworkMarks` (40) + `examMarks` (60) |
| 8 | Automatically calculate grades | `Result.calculate()` → A(5.0), B(4.0), C(3.0), D(2.0), F(0.0) |
| 9 | Generate transcripts | `Student/transcript.html` printable + `/student/transcript` |
|10| Monitor academic performance | `AcademicService.calculateGPA()` + standing |
|11| Generate academic reports | `Admin/reports`, `Lecturer/reports`, `ApiController` |
|12| Role-based authentication | `Role` enum + `SecurityConfig` + `CustomUserDetailsService` |
|13| Maintain secure records | BCrypt `PasswordEncoder`, enabled flag, JPA validation |
|14| Dashboards for different users | `/admin/dashboard`, `/lecturer/dashboard`, `/student/dashboard` |

Grade logic (Ugandan): >=80 A(5), 70-79 B(4), 60-69 C(3), 50-59 D(2), <50 F(0) → GPA = Σ(gradePoint*credits)/Σcredits

## 3. Target Users & Capabilities

**Administrator:** manage students/lecturers/departments/programs/courses/years/semesters/accounts/reports  
**Lecturer:** view assigned courses, view registered students, record attendance, enter CW/exam marks, view performance, course reports  
**Student:** login, view profile, register courses, view attendance, view results/GPA, download transcript

## 4. Project Structure

```
DCSYear1Sem2/oop/
├── pom.xml
├── src/main/java/com/smartcampus/
│   ├── SmartCampusApplication.java
│   ├── model/ (User, Role, Department, Program, AcademicYear, Semester, Student, Lecturer, Course, Enrollment, Attendance, Result)
│   ├── repository/ (JpaRepository for each)
│   ├── service/ (AcademicService, CustomUserDetailsService)
│   ├── controller/ (Home, Admin, Lecturer, Student, Api)
│   └── config/ (SecurityConfig, DataInitializer)
├── src/main/resources/
│   ├── application.properties (H2 + MySQL switch)
│   └── templates/ (index, login, admin/*, lecturer/*, student/*)
└── target/ (build output)
```

## 5. Quick Start

### Prerequisites: Java 17+, Maven
Maven is installed at `~/tools/maven` (or install via `sudo apt install maven`).

```bash
cd ~/DCSYear1Sem2/oop
# Build (already verified BUILD SUCCESS)
~/tools/maven/bin/mvn compile
~/tools/maven/bin/mvn package -DskipTests
# Run
~/tools/maven/bin/mvn spring-boot:run
# OR
java -jar target/smart-campus-system-1.0.0.jar
```

Open: **http://localhost:8080**

- Home: `/` - stats + role cards
- H2 Console: `http://localhost:8080/h2-console` JDBC `jdbc:h2:mem:smartcampusdb` user `sa`
- API Health: `http://localhost:8080/api/health`
- API Students: `http://localhost:8080/api/students`

### Demo Logins (seeded by DataInitializer.java)

| Role | Username | Password | Redirect |
|------|----------|----------|----------|
| ADMIN | admin | admin123 | /admin/dashboard |
| LECTURER | lecturer1 | lecturer123 | /lecturer/dashboard |
| STUDENT | student1 | student123 | /student/dashboard |
| STUDENT | student2 | student123 | /student/dashboard |

Sample data: 2 departments, 2 programs, 5 courses (CSC211 OOP, CSC212 DCN, CSC213 DB, MGT211 MIS, CSC214 Project), 2 students (2025/DCS/DAY/0146 Tonny, 0147 Amina), 1 lecturer, enrollments + 3 results for Tonny (GPA auto).

## 6. MySQL Production Switch

In `application.properties` uncomment:
```
spring.datasource.url=jdbc:mysql://localhost:3306/smartcampus?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```
Create DB: `CREATE DATABASE smartcampus;`

## 7. Key API Endpoints

```
GET  /api/health
GET  /api/students
GET  /api/courses
GET  /api/student/{id}/gpa
```

## 8. For eCampus Submission (GitHub Link)

```bash
cd ~/DCSYear1Sem2/oop
git init
git add .
git commit -m "Smart Campus System - OOP Coursework - Ssegawa Tonny 2025/DCS/DAY/0146"
git branch -M main
git remote add origin https://github.com/<your-username>/smart-campus-system.git
git push -u origin main
```

Then submit the GitHub URL on eCampus before 25 Sept 2026.

## 9. Verification

```
mvn compile - BUILD SUCCESS (2026-09-11 11:58, 33 source files, Java 17, Spring Boot 3.3.5)
Templates: 21 Thymeleaf + Bootstrap 5
Test run: DataInitializer seeds and http://localhost:8080 renders
```

## 10. Extensions (Optional Desktop JavaFX / extra)

- Add `spring-boot-starter-validation` already included for form validation
- Add PDF transcript export via iText
- Add fees module entity if required
- Alternative `JavaFX` frontend can reuse same `model` + `service` packages

---
**Author:** Ssegawa Tonny - 2025/DCS/DAY/0146  
**Course:** OOP Java - Smart Campus  
**Stored:** `~/DCSYear1Sem2/oop` as requested (contains oop/ PDFs preserved)
