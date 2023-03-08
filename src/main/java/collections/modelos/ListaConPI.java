package collections.modelos;

public interface ListaConPI<E> {// Todas las operaciones con Q(1)
    /**
     * Inserta un elemento en una Lista, antes del Elemento
     * en PI, que permanece inalterado
     * @param e Objeto a insertar
     */
    void insertar(E e);

    /**
     * Si !esFin(): elimina de la lista
     * el elemento en PI, que permanece inalterado
     */
    void eliminar();

    /**
     * Si !esFin(): obtiene el elemento en PI
     * @return Objeto en PI
     */
    E recuperar();

    /**
     * Situa el PI de la lista en su inicio
     */
    void inicio();

    /**
     * Si !esFin(): Avanza el PI en la lista
     */
    void siguiente();

    /**
     * Situa el PI de la lista en el final
     */
    void fin(); // sitúa el PI de una Lista en su fin

    /**
     * Comprueba si el PI está en el final de la lista
     */
    boolean esFin(); // comprueba si el PI está en su fin

    /**
     * Comprueba si la lista está vacía
     * @return true si está vacía; false en otro caso
     */
    boolean esVacia(); // comprueba si una Lista Con PI está vacía


    /**
     * Devuelve la talla de la lista
     * @return talla de la lista
     */
    int talla();
}