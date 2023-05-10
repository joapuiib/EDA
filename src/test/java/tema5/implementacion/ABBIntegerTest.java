package tema5.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}