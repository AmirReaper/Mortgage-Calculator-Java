package com.example;

import java.util.Scanner;

/**
 * Console helper for safe numeric input with range validation.
 */
public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static double readNumber(String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().replace(",", "").trim();
                double value = Double.parseDouble(input);
                if (value < min || value > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        String s = scanner.nextLine().trim();
        while (s.isEmpty()) {
            System.out.println("Input cannot be empty.");
            System.out.print(prompt);
            s = scanner.nextLine().trim();
        }
        return s;
    }

    public static String readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim().toLowerCase();
            if (s.equals("y") || s.equals("yes")) return "yes";
            if (s.equals("n") || s.equals("no")) return "no";
            System.out.println("Please enter yes/no (y/n).");
        }
    }
}
