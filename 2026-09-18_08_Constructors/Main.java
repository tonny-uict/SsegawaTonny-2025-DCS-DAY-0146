// Day 2026-09-18 - Module 3: Constructors
class Student {
    String name, regNo;
    // Constructor
    Student(String name, String regNo) {
        this.name = name;
        this.regNo = regNo;
    }
    void display() { System.out.println(regNo + " - " + name); }
}
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Ssegawa Tonny", "2025/DCS/DAY/0146");
        Student s2 = new Student("Musa Ali", "2025/DCS/DAY/0148");
        s1.display();
        s2.display();
        System.out.println("Objects created via constructor with 'this' keyword");
    }
}
