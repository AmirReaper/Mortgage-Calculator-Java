package com.example;

import java.util.Scanner;

public class Console {
    /**
     * Reads a number from user input safely and validates it.
     * Handles incorrect inputs and numbers outside the specified range.
     *
     * @param prompt Message to show the user
     * @param min    Minimum allowed value
     * @param max    Maximum allowed value
     * @return Validated user input
     */
    public static Scanner scanner = new Scanner(System.in);
    public static double readNumber(String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().replace(",", "").trim(); // Remove commas and whitespace
            try {
                value = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (value >= min && value <= max) {
                break;
            } else {
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            }
        }

        return value;
    }
    public static double readNumber(String prompt) {
        return scanner.nextDouble();
    }
}
