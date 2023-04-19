package tema4.ejercicios.colaprioridad;

import org.junit.jupiter.api.Test;
import tema4.implementacion.LEGColaPrioridad;
import tema4.implementacion.MonticuloBinario;
import tema4.modelos.ColaPrioridad;

import static org.junit.jupiter.api.Assertions.*;
import static tema4.ejercicios.colaprioridad.Problema3.cPLineal;

class Problema3Test {

    @Test
    void cPLinealTrueTest() {
        ColaPrioridad<Double> cp = new MonticuloBinario<>();
        cp.insertar(0.3);
        cp.insertar(0.31);
        cp.insertar(0.34);
        assertEquals(true, cPLineal(cp, 0.05));
    }

    @Test
    void cPLinealFalseTest() {
        ColaPrioridad<Double> cp = new MonticuloBinario<>();
        cp.insertar(0.3);
        cp.insertar(0.31);
        cp.insertar(0.34);
        assertEquals(false, cPLineal(cp, 0.02));
    }
}