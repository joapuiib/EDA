package tema5.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ABBMapOrdenadoTest {
    private ABBMapOrdenado<String, Integer> mapOrdenado;

    @Nested
    class EmptyMapOrdenado {
        @BeforeEach
        void setup() {
            mapOrdenado = new ABBMapOrdenado<>();
        }
        @Test
        void insertar(){
            int tallaBefore = mapOrdenado.talla();
            mapOrdenado.insertar("A", 1);
            int tallaAfter = mapOrdenado.talla();
            assertAll(
                    () -> assertEquals(tallaBefore + 1, tallaAfter)
            );
        }
    }
    @Nested
    class PopulatedMapOrdenado {

        @BeforeEach
        void setup() {
            mapOrdenado = new ABBMapOrdenado<>();
            mapOrdenado.insertar("C", 3);
            mapOrdenado.insertar("A", 5);
            mapOrdenado.insertar("R", 1);
            mapOrdenado.insertar("E", 2);
            mapOrdenado.insertar("B", 3);
            mapOrdenado.insertar("S", 10);
            mapOrdenado.insertar("D", 4);
        }

        @ParameterizedTest
        @CsvSource(value = {
                "C, 3",
                "A, 5",
                "S, 10",
                "O, null",
        }, nullValues = "null")
        void recuperar(String clave, Integer expectedValor) {
            assertEquals(expectedValor, mapOrdenado.recuperar(clave));
        }
        @Test
        void actualitzar() {
            Integer old = mapOrdenado.insertar("A", 1);
            assertAll(
                    () -> assertEquals(1, mapOrdenado.recuperar("A")),
                    () -> assertEquals(5, old)

            );
        }
        @Test
        void claves() {
            String claves = mapOrdenado.claves().toString();
            assertEquals("[A, B, C, D, E, R, S]", claves);
        }
        @Test
        void recuperarMin() {
            String min = mapOrdenado.recuperarMin();
            assertEquals("A", min);
        }
        @Test
        void recuperarMax() {
            String max = mapOrdenado.recuperarMax();
            assertEquals("S", max);
        }
    }

}