package com.example.calculator;

import java.util.Scanner;

public class CalculatorApp {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // Non-interactive mode, e.g.: java -jar calculator.jar 5 + 3
        if (args.length == 3) {
            try {
                double a = Double.parseDouble(args[0]);
                double b = Double.parseDouble(args[2]);
                System.out.println("Result: " + calculator.calculate(a, args[1], b));
            } catch (RuntimeException e) {
                System.err.println("Error: " + e.getMessage());
                System.exit(1);
            }
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Simple Calculator (operators: + - * / % ^). Type 'exit' to quit.");
            while (true) {
                System.out.print("Enter expression (e.g. 5 + 3): ");
                if (!scanner.hasNextLine()) {
                    break;
                }
                String line = scanner.nextLine().trim();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                String[] parts = line.split("\\s+");
                if (parts.length != 3) {
                    System.out.println("Invalid format. Use: <number> <operator> <number>");
                    continue;
                }
                try {
                    double a = Double.parseDouble(parts[0]);
                    double b = Double.parseDouble(parts[2]);
                    System.out.println("Result: " + calculator.calculate(a, parts[1], b));
                } catch (RuntimeException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        System.out.println("Goodbye!");
    }
}
