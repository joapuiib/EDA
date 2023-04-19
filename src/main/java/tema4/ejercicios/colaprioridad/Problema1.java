package tema4.ejercicios.colaprioridad;

import tema4.implementacion.LEGColaPrioridad;
import tema4.implementacion.MonticuloBinario;
import tema4.modelos.ColaPrioridad;

public class Problema1 {
    public static <E extends Comparable<E>> void cPsort(E[] v){
        ColaPrioridad<E> colaPrioridad = new MonticuloBinario<>();
        for (E e : v) {
            colaPrioridad.insertar(e);
        }

        int i = 0;
        while(!colaPrioridad.esVacia()){
            E e = colaPrioridad.eliminarMin();
            v[i++] = e;
        }
    }
}
