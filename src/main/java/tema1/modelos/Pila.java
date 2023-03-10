package tema1.modelos;

public interface Pila<E> {
    void apilar(E item);

    E desapilar();

    E tope();

    boolean esVacia();

    int talla();
}
