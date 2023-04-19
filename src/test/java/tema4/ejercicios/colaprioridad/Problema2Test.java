package tema4.ejercicios.colaprioridad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tema1.modelos.ListaConPI;
import tema4.implementacion.LEGColaPrioridad;
import tema4.implementacion.MonticuloBinario;
import tema4.modelos.ColaPrioridad;

import static org.junit.jupiter.api.Assertions.*;
import static tema4.ejercicios.colaprioridad.Problema2.cPFusionar;

class Problema2Test {

    ColaPrioridad<Integer> cp1;
    ColaPrioridad<Integer> cp2;

    @BeforeEach
    void setup(){
        cp1 = new MonticuloBinario<>();
        cp1.insertar(1);
        cp1.insertar(5);
        cp1.insertar(10);
        cp1.insertar(11);
        cp2 = new MonticuloBinario<>();
        cp1.insertar(2);
        cp1.insertar(4);
        cp1.insertar(6);
    }

    @Test
    void cpFusionarTest(){
        ListaConPI<Integer> l = cPFusionar(cp1, cp2);
        assertEquals("[1, 2, 4, 5, 6, 10, 11]", l.toString());
    }

}