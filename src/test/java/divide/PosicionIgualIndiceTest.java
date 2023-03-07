package divide;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static divide.PosicionIgualIndice.ej3;
import static org.junit.jupiter.api.Assertions.*;

class PosicionIgualIndiceTest {

    @ParameterizedTest
    @MethodSource("provideArguments")
    void ej3Test(int[] v, int k) {
        assertEquals(k, ej3(v));
    }

    static Stream<Arguments> provideArguments(){
        return Stream.of(
                Arguments.arguments(new int[]{1}, -1),
                Arguments.arguments(new int[]{-3, -2, -1, 1, 4, 5}, 4),
                Arguments.arguments(new int[]{0, 2, 3, 4, 5, 6, 7}, 0),
                Arguments.arguments(new int[]{-1, 0, 1, 2, 3, 4, 6}, 6)
        );
    }
}