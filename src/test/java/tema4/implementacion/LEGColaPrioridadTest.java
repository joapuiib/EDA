package tema4.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tema4.modelos.ColaPrioridad;

import static org.junit.jupiter.api.Assertions.*;

class LEGColaPrioridadTest {

    ColaPrioridad<Integer> colaPrioridad;

    @BeforeEach
    void setup(){
        colaPrioridad = new LEGColaPrioridad<>();
        colaPrioridad.insertar(2);
        colaPrioridad.insertar(4);
        colaPrioridad.insertar(6);
    }

    @Test
    void insertar3() {
        colaPrioridad.insertar(3);
        assertEquals("[2, 3, 4, 6]", colaPrioridad.toString());
    }

    @Test
    void insertar8() {
        colaPrioridad.insertar(8);
        assertEquals("[2, 4, 6, 8]", colaPrioridad.toString());
    }

    @Test
    void recuperarMin1() {
        colaPrioridad.insertar(1);
        assertEquals(1, colaPrioridad.recuperarMin());
    }

    @Test
    void recuperarMin2() {
        assertEquals(2, colaPrioridad.recuperarMin());
    }

    @Test
    void eliminarMin2() {
        Integer el = colaPrioridad.eliminarMin();
        assertAll(
            () -> assertEquals(2, el),
            () -> assertEquals("[4, 6]", colaPrioridad.toString())
        );
    }
}