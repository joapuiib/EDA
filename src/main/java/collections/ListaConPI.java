package collections;

public interface ListaConPI<E> {// Todas las operaciones con Q(1)
    /**
     * inserta e en una Lista, antes del Elemento
     * en PI, que permanece inalterado
     */
    void insertar(E e);

    void eliminar(); // SII !esFin(): elimina de una Lista el
    // Elemento en PI, que permanece inalterado

    E recuperar(); // SII !esFin(): obtiene el Elemento en PI

    void inicio(); // sitúa el PI de una Lista en su inicio

    void siguiente(); // SII !esFin(): avanza el PI de una Lista

    boolean esFin(); // comprueba si el PI está en su fin

    boolean esVacia(); // comprueba si una Lista Con PI está vacía

    void fin(); // sitúa el PI de una Lista en su fin

    int talla(); // devuelve la talla de una Lista Con PI
}