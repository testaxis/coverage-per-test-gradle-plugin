package com.example;

public class Counter {
    private int count;

    public Counter(int initialValue) {
        this.count = initialValue;
    }

    public int getCount() {
        return count;
    }

    public void increase() {
        count++;
    }

    public void decrease() {
        count--;
    }
}
