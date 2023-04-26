package tema4.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static tema4.implementacion.MonticuloBinario.esHeap;

class MonticuloBinarioTest {

    MonticuloBinario<Integer> monticulo;
    MonticuloBinario<Integer> empty;

    @BeforeEach
    void setup(){
        empty = new MonticuloBinario<>();
        monticulo = new MonticuloBinario<>();
        monticulo.insertar(1);
        monticulo.insertar(7);
        monticulo.insertar(2);
        monticulo.insertar(9);
        monticulo.insertar(8);
        monticulo.insertar(6);
        monticulo.insertar(3);
    }

    @ParameterizedTest
    @CsvSource({
            "1, true",
            "2, true",
            "3, true",
            "4, false",
            "5, false",
            "6, true",
            "7, true",
            "8, true",
            "9, true",
            "10, false",
    })
    void estaEn(int i, boolean expected) {
        boolean actual = monticulo.estaEn(i);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 2",
            "2, 3",
            "3, 4",
            "4, 5",
    })
    void igualesAlMinimo(int times, int expected) {
        for (int i = 0; i < times; i++) {
            monticulo.insertar(1);
        }
        int actual = monticulo.igualesAlMinimo();
        assertEquals(expected, actual);
    }

    @Test
    void borrarHojasenRango() {
        monticulo.borrarHojasenRango(4, 8);
        assertEquals("[1, 3, 2, 9, 7]", monticulo.toString());
    }

    @ParameterizedTest
    @MethodSource("provideHeaps")
    void esHeapTest(Integer[] v, boolean expected){
        boolean actual = esHeap(v);
        assertEquals(expected, actual);
    }
    static Stream<Arguments> provideHeaps(){
        return Stream.of(
                arguments(new Integer[]{1, 2, 3, 4, 5, 6}, true),
                arguments(new Integer[]{1, 2, 10, 4, 11, 20, 15}, true),
                arguments(new Integer[]{5, 4, 3, 4, 5, 6}, false),
                arguments(new Integer[]{1, 10, 3, 4, 5, 6}, false),
                arguments(new Integer[]{1, 2, 3, 4, 5, 6, 1}, false)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0",
            "2, 1",
            "3, 2",
            "4, 3",
            "5, 3",
            "6, 3",
            "7, 4",
            "8, 5",
            "9, 6",
            "10, 7",
    })
    void menoresQue(int i, int expected) {
        int actual = monticulo.menoresQue(i);
        assertEquals(expected, actual);
    }
}