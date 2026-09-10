// Day 2026-09-10 - Module 2: Control Flow - Grade Checker with Loop
// Concept: if-else, for loop, Scanner
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many students? ");
        int count = sc.nextInt();

        for (int i = 1; i <= count; i++) {
            System.out.print("Enter marks for student " + i + ": ");
            int marks = sc.nextInt();
            String grade;
            if (marks >= 80) grade = "D1";
            else if (marks >= 70) grade = "C2";
            else if (marks >= 60) grade = "C3";
            else if (marks >= 50) grade = "P7";
            else grade = "F9";
            System.out.println(" -> Grade: " + grade);
        }
        System.out.println("Done checking " + count + " students");
        sc.close();
    }
}
