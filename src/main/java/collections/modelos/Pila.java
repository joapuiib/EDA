package collections.modelos;

public interface Pila<E> {
    void push(E item);

    E pop();

    E first();

    boolean esVacia();

    int talla();
}
