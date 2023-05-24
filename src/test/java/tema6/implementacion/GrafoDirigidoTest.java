package tema6.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GrafoDirigidoTest {

    private GrafoDirigido gd;

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
    }

    @Test
    void numVertices(){
        assertEquals(6, gd.numVertices());
    }
    @Test
    void numAristas(){
        assertEquals(7, gd.numAristas());
    }

    @ParameterizedTest
    @CsvSource({
        "0, 1, true",
        "0, 2, false",
        "0, 3, false",
        "1, 3, true",
        "1, 4, true",
        "1, 5, false",
        "1, 0, false",
        "3, 0, true",
        "3, 4, true",
        "3, 3, false",
        "3, 5, false",
        "5, 1, true",
        "5, 2, true",
        "5, 4, false",
        "5, 5, false",
    })
    void existeArista(int i, int j, boolean expected){
        assertEquals(expected, gd.existeArista(i, j));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 2",
            "2, 0",
            "3, 2",
            "4, 0",
            "5, 2",
    })
    void gradoSalida(int i, int expected){
        assertEquals(expected, gd.gradoSalida(i));
    }

    @Test
    void gradoSalida(){
        assertEquals(2, gd.gradoSalida());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 2",
            "2, 1",
            "3, 1",
            "4, 2",
            "5, 0",
    })
    void gradoEntrada(int i, int expected){
        assertEquals(expected, gd.gradoEntrada(i));
    }

    @Test
    void gradoEntrada(){
        assertEquals(2, gd.gradoEntrada());
    }
}