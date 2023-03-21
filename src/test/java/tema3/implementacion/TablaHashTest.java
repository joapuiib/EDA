package tema3.implementacion;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tema1.modelos.ListaConPI;
import tema3.modelos.Map;

import static org.junit.jupiter.api.Assertions.*;

class TablaHashTest {
    private Map<String, String> map;

    @BeforeEach
    void setup(){
        map = new TablaHash<>(10);
    }

    @Test
    void insertarTest(){
        int tallaInicial = map.talla();
        map.insertar("Test", "1234");
        int tallaFinal = map.talla();

        assertAll(
                () -> assertEquals(0, tallaInicial),
                () -> assertEquals(1, tallaFinal),
                () -> assertEquals(map.recuperar("Test"), "1234")
        );
    }

    @Test
    void insertarDosElementos(){
        int tallaInicial = map.talla();
        map.insertar("Test1", "1234");
        map.insertar("Test2", "4321");
        int tallaFinal = map.talla();

        assertAll(
                () -> assertEquals(0, tallaInicial),
                () -> assertEquals(2, tallaFinal),
                () -> assertEquals(map.recuperar("Test1"), "1234"),
                () -> assertEquals(map.recuperar("Test2"), "4321")
        );
    }

    @Test
    void clavesTest(){
        map.insertar("Test1", "1234");
        map.insertar("Test2", "4321");

        ListaConPI<String> claves = map.claves();

        System.out.println(claves.toString());
        assertAll(
                () -> assertEquals(claves.talla(), 2),
                () -> assertTrue(claves.toString().contains("Test1")),
                () -> assertTrue(claves.toString().contains("Test2"))
        );
    }

}