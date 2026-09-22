// Day 2026-09-22 - Module 2: Arrays - Average Marks
// Ssegawa Tonny - 2025/DCS/DAY/0146 - Simplified Average Calculator
public class Main {
    public static void main(String[] args) {
        int[] marks = {70, 80, 65, 90, 85};

        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum = sum + marks[i];
        }

        double average = sum / 5.0;

        System.out.println("Average Marks = " + average);
    }
}
