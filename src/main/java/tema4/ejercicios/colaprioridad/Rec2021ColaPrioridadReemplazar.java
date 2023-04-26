package tema4.ejercicios.colaprioridad;

import tema1.implementacion.array.ArrayPila;
import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema1.modelos.Pila;
import tema4.modelos.ColaPrioridad;

public class Rec2021ColaPrioridadReemplazar {
    public static <E extends Comparable<E>> void colaPrioridadReemplazar(ColaPrioridad<E> cp, E a, E b){
        Pila<E> pila = new ArrayPila<>();
        while (!cp.esVacia()){
            E cpe = cp.eliminarMin();
            int cmp = cpe.compareTo(a);
            if(cmp == 0){
                pila.apilar(b);
            } else {
                pila.apilar(cpe);
                if(cmp > 0)
                    break;
            }
        }

        while (!pila.esVacia()){
            cp.insertar(pila.desapilar());
        }
    }
}
