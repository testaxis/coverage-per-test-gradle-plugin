package com.example;

public class Calculator {
    private int value;

    public Calculator(int startValue) {
        this.value = startValue;
    }

    public int add(int number) {
        this.value += number;

        return this.value;
    }

    public int subtract(int number) {
        this.value -= number;

        return this.value;
    }
}
