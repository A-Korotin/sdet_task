package org.simbirsoft.sdet_task.test_source;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class FibonacciArgumentSource implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 1),
                Arguments.of(2, 1),
                Arguments.of(3, 2),
                Arguments.of(4, 3),
                Arguments.of(5, 5),
                Arguments.of(6, 8),
                Arguments.of(7, 13),
                Arguments.of(8, 21),
                Arguments.of(9, 34),
                Arguments.of(10, 55),
                Arguments.of(11, 89)
        );
    }
}
