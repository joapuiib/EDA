package tema5.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ABBTest {
    private ABB<Integer> abb;

    @Nested
    class EmptyABB {
        @BeforeEach
        void setup(){
            abb = new ABB<>();
        }

        @Test
        void givenEmptyABB_shouldBeEmpty(){
            assertAll(
                    () -> assertEquals(0, abb.talla()),
                    () -> assertTrue(abb.esVacio())
            );
        }

        @Test
        void givenInsertElement_shouldReturnElement(){
            abb.insertar(1);
            assertAll(
                    () -> assertEquals(1, abb.talla()),
                    () -> assertFalse(abb.esVacio()),
                    () -> assertEquals(1, abb.recuperar(1))
            );
        }
    }


    @Nested
    class PopulatedABB{
        @BeforeEach
        void setup(){
            abb = new ABB<>();
            abb.insertar(5);
            abb.insertar(2);
            abb.insertar(1);
            abb.insertar(3);
            abb.insertar(7);
            abb.insertar(9);
        }

        @Test
        void recuperarMin(){
            assertEquals(1, abb.recuperarMin());
        }

        @Test
        void eliminarMin(){
            int tallaAnterior = abb.talla();
            assertAll(
                () -> assertEquals(1, abb.eliminarMin()),
                () -> assertEquals(tallaAnterior - 1, abb.talla())
            );
        }
    }
}