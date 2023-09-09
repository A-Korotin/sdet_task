package org.simbirsoft.sdet_task.service.impl;

import org.simbirsoft.sdet_task.service.FibonacciService;
import org.springframework.stereotype.Service;

@Service
public class FibonacciServiceImpl implements FibonacciService {

    @Override
    public long calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        long n1 = 0,
                n2 = 1,
                result = n;
        for (int i = 1; i < n; ++i) {
            result = n1 + n2;
            n1 = n2;
            n2 = result;
        }

        return result;
    }

}
