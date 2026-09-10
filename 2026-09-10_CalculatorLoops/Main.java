// Day 2026-09-10 - Module 2: Calculator with Loops + User Input (Scanner)
// Concept: loops, switch, methods, Scanner
import java.util.Scanner;

class Calculator {
    int add(int a, int b) { return a + b; }
    int sub(int a, int b) { return a - b; }
    int mul(int a, int b) { return a * b; }
    String div(int a, int b) {
        if (b == 0) return "Cannot divide by zero";
        return String.valueOf((double)a / b);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();
        System.out.println("=== Simple Calculator (loops) ===");
        System.out.println("Student: Ssegawa Tonny - 2025/DCS/DAY/0146");

        // Loop menu - 3 calculations
        for (int i = 1; i <= 3; i++) {
            System.out.println("\n--- Calculation " + i + " ---");
            System.out.print("Enter first number: ");
            int a = sc.nextInt();
            System.out.print("Enter second number: ");
            int b = sc.nextInt();
            System.out.print("Choose (+ - * /): ");
            String op = sc.next();

            switch (op) {
                case "+": System.out.println("Result: " + calc.add(a,b)); break;
                case "-": System.out.println("Result: " + calc.sub(a,b)); break;
                case "*": System.out.println("Result: " + calc.mul(a,b)); break;
                case "/": System.out.println("Result: " + calc.div(a,b)); break;
                default: System.out.println("Invalid operator");
            }
        }
        System.out.println("\nDone! Loop finished.");
        sc.close();
    }
}
