// Day 2026-09-10 - Module 2: Loops - Multiplication Table
// Concept: for loop, nested loop
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number for table: ");
        int n = sc.nextInt();

        System.out.println("Multiplication table for " + n + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
        }

        System.out.println("\nAll tables 1-5 (nested loop):");
        for (int a = 1; a <= 5; a++) {
            for (int b = 1; b <= 5; b++) {
                System.out.print((a*b) + "\t");
            }
            System.out.println();
        }
        sc.close();
    }
}
