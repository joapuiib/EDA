package tema4.ejercicios.colaprioridad;

import tema1.implementacion.array.ArrayPila;
import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema1.modelos.Pila;
import tema4.modelos.ColaPrioridad;

public class Parcial2021ColaPrioridadToLista {
    public static <E extends Comparable<E>> ListaConPI<E> colaPrioridadToLista(ColaPrioridad<E> cp, E e){
        ListaConPI<E> l = new LEGListaConPI<>();
        Pila<E> pila = new ArrayPila<>();

        E ant = null;
        while (!cp.esVacia()){
            E cpe = cp.eliminarMin();
            pila.apilar(cpe);

            int cmp = e.compareTo(cpe);
            if(cmp < 0 && (ant == null || !ant.equals(cpe))){
                l.insertar(cpe);
            }
            ant = cpe;
        }

        while (!pila.esVacia()){
            cp.insertar(pila.desapilar());
        }
        return l;
    }
}
