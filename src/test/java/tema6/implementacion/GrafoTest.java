package tema6.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GrafoTest {

    private Grafo gd;
    private Grafo gd2;

    private Grafo noConexo;

    @BeforeEach
    void setup(){
        gd = new GrafoDirigido(6);
        gd.insertarArista(0, 1);
        gd.insertarArista(1, 3);
        gd.insertarArista(1, 4);
        gd.insertarArista(3, 0);
        gd.insertarArista(3, 4);
        gd.insertarArista(5, 1);
        gd.insertarArista(5, 2);

        gd2 = new GrafoDirigido(9);
        gd2.insertarArista(0, 2);
        gd2.insertarArista(0, 3);
        gd2.insertarArista(0, 4);
        gd2.insertarArista(0, 5);
        gd2.insertarArista(1, 5);
        gd2.insertarArista(1, 8);
        gd2.insertarArista(3, 6);
        gd2.insertarArista(3, 8);
        gd2.insertarArista(4, 3);
        gd2.insertarArista(4, 8);
        gd2.insertarArista(6, 7);
        gd2.insertarArista(7, 2);
        gd2.insertarArista(8, 6);

        noConexo = new GrafoDirigido(6);
        noConexo.insertarArista(0, 1);
        noConexo.insertarArista(1, 0);
        noConexo.insertarArista(1, 2);
        noConexo.insertarArista(2, 0);

        noConexo.insertarArista(4, 5);
    }

    @Test
    void toArrayDFS(){
        assertAll(
            () ->assertEquals("[0, 1, 3, 4, 2, 5]", Arrays.toString(gd.toArrayDFS())),
            () ->assertEquals("[0, 2, 3, 6, 7, 8, 4, 5, 1]", Arrays.toString(gd2.toArrayDFS()))
        );
    }
    @Test
    void toArrayBFS(){
        assertAll(
                () ->assertEquals("[0, 1, 3, 4, 2, 5]", Arrays.toString(gd.toArrayBFS())),
                () ->assertEquals("[0, 2, 3, 4, 5, 6, 8, 7, 1]", Arrays.toString(gd2.toArrayBFS()))
        );
    }

    @Test
    void esConexo(){
        assertAll(
                () ->assertTrue(gd.esConexo(), "gd hauria de ser connexe"),
                () ->assertTrue(gd2.esConexo(), "gd2 hauria de ser connexe"),
                () ->assertFalse(noConexo.esConexo(), "noConexe hauria de no ser connexe")
        );
    }
}