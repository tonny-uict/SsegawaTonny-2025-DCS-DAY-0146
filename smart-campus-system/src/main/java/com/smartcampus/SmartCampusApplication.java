package com.smartcampus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartCampusApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartCampusApplication.class, args);
        System.out.println("===============================================");
        System.out.println(" Smart Campus System Started ");
        System.out.println(" http://localhost:8080 ");
        System.out.println(" H2 Console: http://localhost:8080/h2-console (JDBC: jdbc:h2:mem:smartcampusdb)");
        System.out.println(" Default logins: admin/admin123 | lecturer1/lecturer123 | student1/student123");
        System.out.println("===============================================");
    }
}
