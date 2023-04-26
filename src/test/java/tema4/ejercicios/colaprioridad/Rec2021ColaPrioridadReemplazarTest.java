package tema4.ejercicios.colaprioridad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tema4.implementacion.LEGColaPrioridad;
import tema4.modelos.ColaPrioridad;

import static org.junit.jupiter.api.Assertions.*;
import static tema4.ejercicios.colaprioridad.Rec2021ColaPrioridadReemplazar.colaPrioridadReemplazar;

class Rec2021ColaPrioridadReemplazarTest {

    private ColaPrioridad<Integer> cp;

    @BeforeEach
    void setup(){
        cp = new LEGColaPrioridad<>();
        cp.insertar(2);
        cp.insertar(2);
        cp.insertar(3);
        cp.insertar(4);
        cp.insertar(4);
        cp.insertar(7);
        cp.insertar(7);
        cp.insertar(9);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1: 2: [2, 2, 3, 4, 4, 7, 7, 9]",
            "2: 5: [3, 4, 4, 5, 5, 7, 7, 9]",
            "3: 10: [2, 2, 4, 4, 7, 7, 9, 10]",
            "3: 1: [1, 2, 2, 4, 4, 7, 7, 9]",
    }, delimiter = ':')
    void colaPrioridadReemplazarTest(int a, int b, String expected) {
        colaPrioridadReemplazar(cp, a, b);
        assertEquals(expected, cp.toString());
    }
}