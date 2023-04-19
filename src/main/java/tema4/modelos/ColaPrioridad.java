package tema4.modelos;

public interface ColaPrioridad<E extends Comparable <E>> {
    void insertar(E e);

    /** SII !esVacia() */
    E eliminarMin();

    /** SII !esVacia() */
    E recuperarMin();

    boolean esVacia();
}
