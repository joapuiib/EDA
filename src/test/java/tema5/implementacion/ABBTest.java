package tema5.implementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;

import java.util.List;
import java.util.NoSuchElementException;

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
            Integer old = abb.insertar(1);
            assertAll(
                    () -> assertEquals(1, abb.talla()),
                    () -> assertFalse(abb.esVacio()),
                    () -> assertEquals(1, abb.recuperar(1)),
                    () -> assertNull(old)
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
        @Test
        void recuperarMax(){
            assertEquals(9, abb.recuperarMax());
        }

        @Test
        void eliminarMax(){
            int tallaAnterior = abb.talla();
            assertAll(
                    () -> assertEquals(9, abb.eliminarMax()),
                    () -> assertEquals(tallaAnterior - 1, abb.talla())
            );
        }

        @ParameterizedTest
        @CsvSource(value = {
                "0, 1",
                "1, 2",
                "2, 3",
                "3, 5",
                "4, 5",
                "5, 7",
                "7, 9",
                "9, null",
        }, nullValues = "null")
        void sucesor(Integer e, Integer expectedSucesor){
            assertEquals(expectedSucesor, abb.sucesor(e));
        }
        @ParameterizedTest
        @CsvSource(value = {
                "0, null",
                "1, null",
                "2, 1",
                "3, 2",
                "4, 3",
                "5, 3",
                "7, 5",
                "9, 7",
                "10, 9"
        }, nullValues = "null")
        void predecesor(Integer e, Integer expectedPredecesor){
            assertEquals(expectedPredecesor, abb.predecesor(e));
        }

        @Test
        void toStringInOrder(){
            assertEquals("[1, 2, 3, 5, 7, 9]", abb.toStringInOrder());
        }
        @Test
        void inOrder(){
            assertEquals("[1, 2, 3, 5, 7, 9]", abb.inOrder().toString());
        }

        @ParameterizedTest
        @CsvSource(value = {
                "0, null",
                "1, 2",
                "2, 5",
                "3, 2",
                "4, null",
                "5, null",
                "7, 5",
                "9, 7",
                "10, null"
        }, nullValues = "null")
        void padreDe(Integer e, Integer expectedPadre){
            assertEquals(expectedPadre, abb.padreDe(e));
        }

        @ParameterizedTest
        @CsvSource(value = {
                "0, null",
                "1, 3",
                "2, 7",
                "3, 1",
                "4, null",
                "5, null",
                "7, 2",
                "9, null",
                "10, null"
        }, nullValues = "null")
        void hermanoDe(Integer e, Integer expectedPadre){
            assertEquals(expectedPadre, abb.hermanoDe(e));
        }

        @ParameterizedTest
        @CsvSource(value = {
                "0: [5]",
                "1: [2, 7]",
                "2: [1, 3, 9]",
                "3: []",
        }, delimiter = ':', nullValues = "null")
        void datosEnNivel(Integer e, String expectedDatos){
            assertEquals(expectedDatos, abb.datosEnNivel(e));
        }

        @ParameterizedTest
        @CsvSource(value = {
                "0: [5]",
                "1: [2, 7]",
                "2: [1, 3, 9]",
                "3: []",
        }, delimiter = ':', nullValues = "null")
        void datosEnNivelLista(Integer e, String expectedDatos){
            assertEquals(expectedDatos, abb.datosEnNivelLista(e).toString());
        }

        @Test
        void alturaEquilibrado_givenEquilibrado_shouldReturnAltura(){
            assertEquals(2, abb.alturaDeEquilibrado());
        }
        @Test
        void alturaEquilibrado_givenEquilibradoLeft_shouldReturnAltura(){
            abb.insertar(4);
            assertEquals(3, abb.alturaDeEquilibrado());
        }

        @Test
        void alturaEquilibrado_givenNoEquilibradoLeft_shouldThrowException(){
            abb.insertar(0);
            abb.insertar(-1);
            assertThrows(
                NoSuchElementException.class,
                () -> abb.alturaDeEquilibrado()
            );
        }

        @Test
        void alturaEquilibrado_givenNoEquilibradoRight_shouldThrowException(){
            abb.insertar(10);
            abb.insertar(11);
            assertThrows(
                    NoSuchElementException.class,
                    () -> abb.alturaDeEquilibrado()
            );
        }


        @ParameterizedTest
        @CsvSource(value = {
                "[1, 3, 2, 9, 7, 5]: true",
                "[1, 3, 2, 9, 7]: true",
                "[9, 3, 1]: false",
                "[]: true",
        }, delimiter = ':', nullValues = "null")
        void esPostOrder(String list, boolean expected){
            ListaConPI<Integer> l = new LEGListaConPI<>();
            String listMinusBrackets = list.substring(1, list.length() - 1);
            String[] splitList = listMinusBrackets.split(", ");
            for (String s : splitList)
                if(!s.isEmpty())
                    l.insertar(Integer.parseInt(s));

            assertEquals(expected, abb.esPostOrden(l));
        }

        @ParameterizedTest
        @CsvSource(value = {
                "[2, 3, 7]: true",
                "[2, 3, 4]: false",
                "[1, 3, 2]: false",
                "[]: true",
        }, delimiter = ':', nullValues = "null")
        void contiene(String list, boolean expected){
            ListaConPI<Integer> l = new LEGListaConPI<>();
            String listMinusBrackets = list.substring(1, list.length() - 1);
            String[] splitList = listMinusBrackets.split(", ");
            for (String s : splitList)
                if(!s.isEmpty())
                    l.insertar(Integer.parseInt(s));

            assertEquals(expected, abb.contiene(l));
        }

        @ParameterizedTest
        @CsvSource(value = {
                "0, 5 2 1\\n5 2 3\\n5 7 9",
                "2, 5 2 3\\n5 7 9",
                "5, 5 7 9",
                "10, ",
        }, emptyValue="")
        void caminosAHojas(Integer e, String expectedCamins){
            if(expectedCamins == null)
                expectedCamins = "";
            assertEquals(expectedCamins, abb.caminosAHojas(e).replace("\n", "\\n"));
        }

    }

    @ParameterizedTest
    @CsvSource(value = {
            "12, 10, 14",
            "8, 4, 14",
            "8, 8, 14",
            "20, 10, 22",
            "20, 8, 22",
    })
    void lowestCommonAncestor(Integer expected, Integer e1, Integer e2){
        abb = new ABB<>();
        abb.insertar(20);
        abb.insertar(8);
        abb.insertar(22);
        abb.insertar(4);
        abb.insertar(12);
        abb.insertar(10);
        abb.insertar(14);
        assertEquals(expected, abb.lowestCommonAncestor(e1, e2));
    }
}