package com.example.calculator;

public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    public double modulo(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot perform modulo by zero");
        }
        return a % b;
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public double squareRoot(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Cannot take square root of a negative number");
        }
        return Math.sqrt(a);
    }

    public double calculate(double a, String operator, double b) {
        return switch (operator) {
            case "+" -> add(a, b);
            case "-" -> subtract(a, b);
            case "*" -> multiply(a, b);
            case "/" -> divide(a, b);
            case "%" -> modulo(a, b);
            case "^" -> power(a, b);
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }
}
