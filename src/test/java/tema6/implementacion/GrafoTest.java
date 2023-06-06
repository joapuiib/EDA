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

    private Grafo gdPesos;

    private Grafo topologico;

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

        gdPesos = new GrafoDirigido(7);
        gdPesos.insertarArista(0, 6, 1);
        gdPesos.insertarArista(1, 2, 2);
        gdPesos.insertarArista(1, 4, 4);
        gdPesos.insertarArista(2, 4, 1);
        gdPesos.insertarArista(2, 5, 10);
        gdPesos.insertarArista(3, 1, 4);
        gdPesos.insertarArista(3, 6, 5);
        gdPesos.insertarArista(4, 0, 4);
        gdPesos.insertarArista(4, 3, 2);
        gdPesos.insertarArista(4, 5, 2);
        gdPesos.insertarArista(4, 6, 8);
        gdPesos.insertarArista(5, 0, 6);

        topologico = new GrafoDirigido(9);
        topologico.insertarArista(0, 1);
        topologico.insertarArista(0, 2);
        topologico.insertarArista(0, 3);
        topologico.insertarArista(0, 4);
        topologico.insertarArista(1, 2);
        topologico.insertarArista(3, 7);
        topologico.insertarArista(5, 2);
        topologico.insertarArista(6, 3);
        topologico.insertarArista(8, 3);
        topologico.insertarArista(8, 4);
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

    /*
    @Test
    void esConexo(){
        assertAll(
                () ->assertTrue(gd.esConexo(), "gd hauria de ser connexe"),
                () ->assertTrue(gd2.esConexo(), "gd2 hauria de ser connexe"),
                () ->assertFalse(noConexo.esConexo(), "noConexe hauria de no ser connexe")
        );
    }
    */

    @ParameterizedTest
    @CsvSource(value = {
            "0: 3: [0, 1, 3]",
            "0: 4: [0, 1, 4]",
            "0: 2: []",
            "0: 5: []",
            "3: 4: [3, 4]",
            "3: 1: [3, 0, 1]",
            "5: 0: [5, 1, 3, 0]",
    }, delimiter = ':')
    void caminoMinimoSinPesos(int v, int w, String expectedCamino){
        assertEquals(expectedCamino, gd.caminoMinimoSinPesos(v, w).toString());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0: 1: []",
            "1: 3: [1, 2, 4, 3]",
            "1: 6: [1, 2, 4, 0, 6]",
            "4: 6: [4, 0, 6]",
            "3: 5: [3, 1, 2, 4, 5]",
    }, delimiter = ':')
    void caminoMinimo(int v, int w, String expectedCamino){
        assertEquals(expectedCamino, gdPesos.caminoMinimo(v, w).toString());
    }

    @Test
    void ordenTopologico(){
        assertEquals("[8, 6, 5, 0, 4, 3, 7, 1, 2]", Arrays.toString(topologico.ordenTopologicoDFS()));
    }
}