package org.simbirsoft.sdet_task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.simbirsoft.sdet_task.service.FibonacciService;
import org.simbirsoft.sdet_task.test_source.FibonacciArgumentSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FibonacciTests {
    @Autowired
    private FibonacciService fibonacciService;

    @ParameterizedTest
    @DisplayName("Fibonacci test")
    @ArgumentsSource(FibonacciArgumentSource.class)
    public void test(int n, int expected) {
        long fib = fibonacciService.calculateFibonacci(n);
        Assertions.assertEquals(expected, fib);
    }
}
