// Day 2026-09-14 - Module 3: Classes, Objects, Methods
class Student {
    String name;
    String regNo;
    String course;

    void display() {
        System.out.println(regNo + " | " + name + " | " + course);
    }
}
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "Ssegawa Tonny";
        s1.regNo = "2025/DCS/DAY/0146";
        s1.course = "DCS";
        s1.display();

        Student s2 = new Student();
        s2.name = "Jane Doe";
        s2.regNo = "2025/DCS/DAY/0147";
        s2.course = "DCS";
        s2.display();
    }
}
