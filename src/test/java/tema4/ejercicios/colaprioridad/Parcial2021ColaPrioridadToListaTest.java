package tema4.ejercicios.colaprioridad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tema1.modelos.ListaConPI;
import tema4.implementacion.LEGColaPrioridad;
import tema4.implementacion.MonticuloBinario;
import tema4.modelos.ColaPrioridad;

import static org.junit.jupiter.api.Assertions.*;
import static tema4.ejercicios.colaprioridad.Parcial2021ColaPrioridadToLista.colaPrioridadToLista;

class Parcial2021ColaPrioridadToListaTest {

    private ColaPrioridad<Integer> repetits;
    private ColaPrioridad<Integer> noRepetits;

    @BeforeEach
    void setup(){
        repetits = new LEGColaPrioridad<>();
        repetits.insertar(2);
        repetits.insertar(2);
        repetits.insertar(3);
        repetits.insertar(4);
        repetits.insertar(4);
        repetits.insertar(7);
        repetits.insertar(7);
        repetits.insertar(9);

        noRepetits = new LEGColaPrioridad<>();
        noRepetits.insertar(2);
        noRepetits.insertar(3);
        noRepetits.insertar(4);
        noRepetits.insertar(7);
        noRepetits.insertar(9);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "true: 1: [2, 3, 4, 7, 9]",
            "false: 1: [2, 3, 4, 7, 9]",
            "true: 3: [4, 7, 9]",
            "false: 3: [4, 7, 9]",
            "true: 9: []",
            "false: 9: []"
    }, delimiter = ':')
    void colaPrioridadToListaTest(boolean repetits, int i, String expected) {
        ColaPrioridad<Integer> cp = repetits ? this.repetits : this.noRepetits;
        String before = cp.toString();
        ListaConPI<Integer> l = colaPrioridadToLista(cp, i);
        String after = cp.toString();
        assertAll(
            () ->assertEquals(expected, l.toString()),
            () ->assertEquals(before, after)
        );
    }
}