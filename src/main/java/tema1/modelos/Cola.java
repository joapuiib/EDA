package tema1.modelos;

/**
 * La estructura de datos Cola sigue un política FIFO (First-In First-Out),
 * que básciamente significa que se recuperan los datos en el mismo orden
 * que llegan.
 *
 * Un ejemplo de la utilitzación de esta estructura de datos podría
 * ser una cola en un supermercado: los clientes pasan por caja
 * en el orden de llegada.
 *
 * La interficie Cola especifica el funcionamiento general de una cualquier cola,
 * independentemente de su implementación.
 */
public interface Cola<E> {
    /**
     * Inserta un elemento al final de la cola
     * @param item Elemento que se desea insertar
     */
    void encolar(E item);

    /**
     * Recupera y elimina el siguiente elemento de la cola en salir
     * @return Siguiente elemento de la cola
     */
    E desencolar();

    /**
     * Recupera el siguiente elemento de la cola en salir.
     * Al contrario que {@link #encolar(E item)}, este método no elimina el objeto.
     * @return Siguiente elemento de la cola
     */
    E primero();


    /**
     * Indica si la cola está vacia
     * @return true si la cola está vacía; false en qualquier otro caso
     */
    boolean esVacia();
}
