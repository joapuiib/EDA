package tema4.ejercicios.colaprioridad;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema4.implementacion.LEGColaPrioridad;
import tema4.modelos.ColaPrioridad;

public class Problema2 {
    public static <E extends Comparable<E>> ListaConPI<E> cPFusionar(ColaPrioridad<E> cp1, ColaPrioridad<E> cp2){
        ListaConPI<E> l = new LEGListaConPI<>();
        while (!cp1.esVacia() && !cp2.esVacia()){
            E e1 = cp1.recuperarMin();
            E e2 = cp2.recuperarMin();
            int cmp = e1.compareTo(e2);
            if (cmp < 0){
                cp1.eliminarMin();
                l.insertar(e1);
            } else {
                cp2.eliminarMin();
                l.insertar(e2);
            }
        }
        while (!cp1.esVacia()){
            E e1 = cp1.eliminarMin();
            l.insertar(e1);
        }
        while (!cp2.esVacia()){
            E e2 = cp2.eliminarMin();
            l.insertar(e2);
        }

        return l;
    }
}
