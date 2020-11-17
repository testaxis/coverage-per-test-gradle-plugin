package com.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {
    @Test
    void testAddsNumbers() {
        Calculator calculator = new Calculator(5);

        int result = calculator.add(12);

        assertThat(result).isEqualTo(17);
    }

    @Test
    void testSubtractsNumbers() {
        Calculator calculator = new Calculator(5);

        int result = calculator.subtract(3);

        assertThat(result).isEqualTo(2);
    }
}
