package tema2.ejercicios;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static tema2.ejercicios.Ej11CurvaContinua.calcularK;
import static org.junit.jupiter.api.Assertions.*;

class Ej11CurvaContinuaTest {

    @ParameterizedTest
    @MethodSource("provideArguments")
    void calcularKTest(int[] v, int k) {
        assertEquals(k, calcularK(v));
    }

    static Stream<Arguments> provideArguments(){
        return Stream.of(
                Arguments.arguments(new int[]{-3, -2, -1, 1, 4, 5}, 2),
                Arguments.arguments(new int[]{-1, -1, -1, -1, -1, 0, 1}, 5),
                Arguments.arguments(new int[]{0, 1, 2, 3, 4, 5, 6}, 0)
        );
    }
}