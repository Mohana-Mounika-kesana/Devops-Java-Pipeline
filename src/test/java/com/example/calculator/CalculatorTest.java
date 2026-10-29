package com.example.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {

    private static final double DELTA = 1e-9;
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("Addition")
    class Addition {
        @Test
        void addsPositiveNumbers() {
            assertEquals(5, calculator.add(2, 3), DELTA);
        }

        @Test
        void addsNegativeNumbers() {
            assertEquals(-5, calculator.add(-2, -3), DELTA);
        }

        @Test
        void addsDecimals() {
            assertEquals(0.3, calculator.add(0.1, 0.2), DELTA);
        }
    }

    @Nested
    @DisplayName("Subtraction")
    class Subtraction {
        @Test
        void subtractsNumbers() {
            assertEquals(1, calculator.subtract(3, 2), DELTA);
        }

        @Test
        void resultCanBeNegative() {
            assertEquals(-1, calculator.subtract(2, 3), DELTA);
        }
    }

    @Nested
    @DisplayName("Multiplication")
    class Multiplication {
        @Test
        void multipliesNumbers() {
            assertEquals(6, calculator.multiply(2, 3), DELTA);
        }

        @Test
        void multiplyByZero() {
            assertEquals(0, calculator.multiply(5, 0), DELTA);
        }
    }

    @Nested
    @DisplayName("Division")
    class Division {
        @Test
        void dividesNumbers() {
            assertEquals(2.5, calculator.divide(5, 2), DELTA);
        }

        @Test
        void divideByZeroThrows() {
            ArithmeticException ex = assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
            assertEquals("Cannot divide by zero", ex.getMessage());
        }
    }

    @Nested
    @DisplayName("Other operations")
    class OtherOperations {
        @Test
        void modulo() {
            assertEquals(1, calculator.modulo(10, 3), DELTA);
        }

        @Test
        void moduloByZeroThrows() {
            assertThrows(ArithmeticException.class, () -> calculator.modulo(10, 0));
        }

        @Test
        void power() {
            assertEquals(8, calculator.power(2, 3), DELTA);
        }

        @Test
        void squareRoot() {
            assertEquals(4, calculator.squareRoot(16), DELTA);
        }

        @Test
        void squareRootOfNegativeThrows() {
            assertThrows(IllegalArgumentException.class, () -> calculator.squareRoot(-1));
        }
    }

    @ParameterizedTest(name = "{0} {1} {2} = {3}")
    @CsvSource({
            "2, +, 3, 5",
            "5, -, 3, 2",
            "4, *, 3, 12",
            "9, /, 3, 3",
            "10, %, 4, 2",
            "2, ^, 10, 1024"
    })
    void calculateWithOperator(double a, String op, double b, double expected) {
        assertEquals(expected, calculator.calculate(a, op, b), DELTA);
    }

    @Test
    void calculateWithUnsupportedOperatorThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(1, "&", 2));
    }
}
