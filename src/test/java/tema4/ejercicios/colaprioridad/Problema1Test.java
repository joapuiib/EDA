package tema4.ejercicios.colaprioridad;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static tema4.ejercicios.colaprioridad.Problema1.cPsort;

class Problema1Test {

    @Test
    void cPsortTest() {
        Integer[] a = new Integer[]{1, 4, 5, 3, 2};
        cPsort(a);
        assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(a));
    }
}