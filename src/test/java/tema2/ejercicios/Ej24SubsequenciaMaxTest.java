package tema2.ejercicios;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static tema2.ejercicios.Ej24SubsequenciaMax.subSumaMax;
import static org.junit.jupiter.api.Assertions.*;

class Ej24SubsequenciaMaxTest {

    @ParameterizedTest
    @MethodSource("provideArguments")
    void subSumaMaxTest(int[] v, int sumaMax) {
        assertEquals(sumaMax, subSumaMax(v));
    }

    static Stream<Arguments> provideArguments(){
        return Stream.of(
                Arguments.arguments(new int[]{-2, 3, 4, -3, 5, 6, -2}, 15),
                Arguments.arguments(new int[]{-2, 3, 4, -20, 5, 6, -2}, 11),
                Arguments.arguments(new int[]{-2, 11, -4, 13, -5, 2}, 20),
                Arguments.arguments(new int[]{-3, -2, -1, 1, 4, 5}, 10),
                Arguments.arguments(new int[]{-1, -1, -1, -1, -1, 0, 1}, 1),
                Arguments.arguments(new int[]{0, 1, 2, 3, 4, 5, 6}, 21)
        );
    }
}