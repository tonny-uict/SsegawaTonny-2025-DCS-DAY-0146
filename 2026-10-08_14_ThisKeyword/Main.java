// Day 2026-10-08 - Module 3&4: 'this' keyword demo
class Person {
    private String name; private int age;
    Person(String name, int age) { this.name = name; this.age = age; } // this distinguishes field vs param
    void display() { System.out.println(this.name + " is " + this.age + " years"); }
    Person getThis() { return this; }
}
public class Main {
    public static void main(String[] args) {
        Person p = new Person("Tonny", 22);
        p.display();
        System.out.println("this refers to current object: " + p.getThis());
    }
}
