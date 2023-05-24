package tema6.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrafoTest {

    private Grafo gd;

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
    void toArrayDFS(){
        assertEquals("[0, 1, 3, 4, 2, 5]", Arrays.toString(gd.toArrayDFS()));
    }
    @Test
    void toArrayBFS(){
        assertEquals("[0, 1, 3, 4, 2, 5]", Arrays.toString(gd.toArrayDFS()));
    }
}