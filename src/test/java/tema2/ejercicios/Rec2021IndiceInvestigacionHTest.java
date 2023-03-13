package tema2.ejercicios;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static tema2.ejercicios.Rec2021IndiceInvestigacionH.calcularH;

class Rec2021IndiceInvestigacionHTest {
    @ParameterizedTest
    @MethodSource("provideArguments")
    void calcularHTest(int[] v, int h) {
        assertEquals(h, calcularH(v));
    }

    static Stream<Arguments> provideArguments(){
        return Stream.of(
                Arguments.arguments(new int[]{2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1}, 2),
                Arguments.arguments(new int[]{3, 3, 3, 2, 1}, 3),
                Arguments.arguments(new int[]{10, 9, 8, 7, 6, 3, 2, 1}, 5),
                Arguments.arguments(
                        new int[]{61, 41, 40, 34, 32, 32, 30, 27, 23, 22, 18, 17, 17, 16, 15, 14, 14, 12, 12},
                        15
                )
        );
    }

}