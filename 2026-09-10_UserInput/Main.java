// Day 2026-09-10 - Module 2: User Input with Scanner (Basic)
// Concept: Scanner, variables, data types, println
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== User Input Demo ===");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter reg number: ");
        String reg = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        System.out.print("Enter GPA: ");
        double gpa = sc.nextDouble();

        System.out.println("\n--- Your Details ---");
        System.out.println("Name: " + name);
        System.out.println("RegNo: " + reg);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);

        if (gpa >= 4.0) System.out.println("Excellent!");
        else if (gpa >= 3.0) System.out.println("Good");
        else System.out.println("Keep working");

        sc.close();
    }
}
