package tema3.ejercicios;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static tema3.ejercicios.Parcial2022Umbral.superanUmbral;

class Parcial2022UmbralTest {

    @ParameterizedTest
    @MethodSource("provideArguments")
    void umbralTest(String telefons, int umbral, String expected){
        ListaConPI<String> listaTelefons = new LEGListaConPI<>();
        for (String t : telefons.substring(1, telefons.length() - 1).split(", ")){
            listaTelefons.insertar(t);
        }
        ListaConPI<String> expectedTelefons = new LEGListaConPI<>();
        for (String t : expected.substring(1, expected.length() - 1).split(", ")){
            listaTelefons.insertar(t);
        }
        assertEquals(expectedTelefons, superanUmbral(listaTelefons, umbral));
    }

    static Stream<Arguments> provideArguments(){
        return Stream.of(
                Arguments.arguments("[A, B, C, D, C, A, C, B, D, C, A, E, F, C]", 3, "[C, A]")
        );
    }

}