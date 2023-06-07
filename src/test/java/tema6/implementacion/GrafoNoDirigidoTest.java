package tema6.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrafoNoDirigidoTest {
    GrafoNoDirigido gnd;

    GrafoNoDirigido noConexo;

    @BeforeEach
    void setup(){
        gnd = new GrafoNoDirigido(5);

        noConexo = new GrafoNoDirigido(6);
        noConexo.insertarArista(0, 1);
        noConexo.insertarArista(0, 2);
        noConexo.insertarArista(3, 4);
    }

    @Test
    void insertarArista(){
        gnd.insertarArista(0, 1);
        assertAll(
                () -> assertTrue(gnd.existeArista(0, 1)),
                () -> assertTrue(gnd.existeArista(1, 0))
        );
    }

    @Test
    void insertarAristaPeso(){
        gnd.insertarArista(1, 0, 5.0);
        assertAll(
                () -> assertTrue(gnd.existeArista(0, 1)),
                () -> assertTrue(gnd.existeArista(1, 0)),
                () -> assertEquals(5.0, gnd.pesoArista(1, 0)),
                () -> assertEquals(5.0, gnd.pesoArista(0, 1))
        );
    }

    @Test
    void componentesConexas(){
        assertEquals("[[0, 1, 2], [3, 4], [5]]", noConexo.componentesConexas().toString());
    }
}