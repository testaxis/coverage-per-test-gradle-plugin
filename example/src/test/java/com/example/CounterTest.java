package com.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CounterTest {
    @Test
    void testIncreaseCounter() {
        Counter counter = new Counter(0);

        counter.increase();
        counter.increase();

        assertThat(counter.getCount()).isEqualTo(2);
    }

    @Test
    void testDecreaseCounter() {
        Counter counter = new Counter(3);

        counter.increase();
        counter.decrease();
        counter.decrease();

        assertThat(counter.getCount()).isEqualTo(2);
    }
}
