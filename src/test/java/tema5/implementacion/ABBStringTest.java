package tema5.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ABBStringTest {
    ABBString abb;

    @BeforeEach
    void setup(){
        abb = new ABBString();
        abb.insertar("prisa");
        abb.insertar("precio");
        abb.insertar("jaleo");
        abb.insertar("pretil");
        abb.insertar("java");
        abb.insertar("programa");
        abb.insertar("prusia");
        abb.insertar("psique");
    }

    @ParameterizedTest
    @CsvSource({
            "pr, 5",
            "ja, 2",
            "ps, 1",
            "pre, 2",
            "prisa, 1"
    })
    void testPrefix(String prefix, int expected){
        assertEquals(expected, abb.palabrasQueEmpiezan(prefix));
    }

}