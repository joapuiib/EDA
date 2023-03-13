package tema2.ejercicios;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tema2.ejercicios.Ej12CurvaConcava.calcularK;

class Ej12CurvaConcavaTest {

    @ParameterizedTest
    @MethodSource("provideArguments")
    void calcularKTest(int[] v, int k) {
        assertEquals(k, calcularK(v));
    }

    static Stream<Arguments> provideArguments(){
        return Stream.of(
                Arguments.arguments(new int[]{4, 3, 2, 1, 2, 3, 4, 5, 6, 7}, 3),
                Arguments.arguments(new int[]{2, 1, 2, 3, 4, 5, 6, 7}, 1),
                Arguments.arguments(new int[]{7, 6, 5, 4, 3, 2, 1, 3, 4}, 6)
        );
    }
}