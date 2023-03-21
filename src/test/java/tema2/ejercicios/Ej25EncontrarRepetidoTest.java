package tema2.ejercicios;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tema2.ejercicios.Ej25EncontrarRepetido.encontrarRepetido;

class Ej25EncontrarRepetidoTest {

    @ParameterizedTest
    @MethodSource("provideArguments")
    void ej25Test(int[] v, int k) {
        assertEquals(k, encontrarRepetido(v));
    }

    static Stream<Arguments> provideArguments(){
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 3, 4, 4, 5, 6, 7, 8}, 4),
                Arguments.arguments(new int[]{1, 2, 2, 3, 4, 5, 6, 7, 8}, 2),
                Arguments.arguments(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 8}, 8),
                Arguments.arguments(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, -1),
                Arguments.arguments(new int[]{1, 1, 2, 3, 4}, 1)
        );
    }
}