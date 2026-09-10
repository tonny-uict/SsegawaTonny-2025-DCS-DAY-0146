// Day 2026-09-10 - Module 3 & 4: Class, Constructor, Encapsulation + Scanner
// Concept: class, object, constructor, this, private + getters/setters, Scanner
import java.util.Scanner;

class Student {
    private String name;
    private String regNo;
    private int age;

    // Constructor with this
    public Student(String name, String regNo, int age) {
        this.name = name;
        this.regNo = regNo;
        this.age = age;
    }

    public String getName() { return name; }
    public String getRegNo() { return regNo; }
    public int getAge() { return age; }

    public void setAge(int age) {
        if (age > 0) this.age = age;
        else System.out.println("Invalid age");
    }

    public void display() {
        System.out.println(regNo + " | " + name + " | Age: " + age);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many students to register? ");
        int n = sc.nextInt();
        sc.nextLine(); // clear

        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i+1) + ":");
            System.out.print("Name: "); String name = sc.nextLine();
            System.out.print("RegNo: "); String reg = sc.nextLine();
            System.out.print("Age: "); int age = sc.nextInt(); sc.nextLine();
            students[i] = new Student(name, reg, age);
        }

        System.out.println("\n--- Registered Students ---");
        for (Student s : students) {
            s.display();
        }
        sc.close();
    }
}
