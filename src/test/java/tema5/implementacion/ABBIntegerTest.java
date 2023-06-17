package tema5.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ABBIntegerTest {
    private ABBInteger abb;

    @BeforeEach
    void setup(){
        abb = new ABBInteger();
        abb.insertar(5);
        abb.insertar(2);
        abb.insertar(1);
        abb.insertar(3);
        abb.insertar(7);
        abb.insertar(9);
    }

    @Test
    void cambiarSigno(){
        abb.cambiarSigno();
        assertEquals("[-9, -7, -5, -3, -2, -1]", abb.toStringInOrder());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0: [1, 2, 3, 5, 7, 9]",
            "1: [2, 3, 5, 7, 9]",
            "2: [3, 5, 7, 9]",
            "3: [5, 7, 9]",
            "4: [5, 7, 9]",
            "5: [7, 9]",
            "7: [9]",
            "9: []",
    }, delimiter = ':')
    void numerosMayores(int n, String expectedList){
        assertEquals(expectedList, abb.numerosMayores(n).toString());
    }
}