package tema4.implementacion;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema4.modelos.ColaPrioridad;

public class LEGColaPrioridad<E extends Comparable<E>> extends LEGListaConPI<E> implements ColaPrioridad<E> {

    @Override
    public void insertar(E e) {
        for (inicio(); !esFin(); siguiente()) {
            E e2 = recuperar();
            int cmp = e.compareTo(e2);
            if(cmp < 0){
                break;
            }
        }
        super.insertar(e);
    }

    @Override
    public E eliminarMin() {
        inicio();
        E el = recuperar();
        eliminar();
        return el;
    }

    @Override
    public E recuperarMin() {
        inicio();
        return recuperar();
    }

    @Override
    public boolean esVacia() {
        return super.esVacia();
    }
}
