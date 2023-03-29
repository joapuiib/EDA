package tema1.implementacion.puntointeres;

import tema1.modelos.Pila;

public class LEGPila<E> extends LEGListaConPI<E> implements Pila<E> {

    @Override
    public void apilar(E item) {
        inicio();
        insertar(item);
    }

    @Override
    public E desapilar() {
        inicio();
        E e = recuperar();
        eliminar();
        return e;
    }

    @Override
    public E tope() {
        inicio();
        return recuperar();
    }

    public static void main(String[] args) {
        LEGPila<String> pila = new LEGPila<>();
        pila.apilar("Item1");
        pila.apilar("Item2");
        pila.apilar("Item3");
        pila.apilar("Item4");

        int i = 0;
        while (!pila.esVacia()) {
            System.out.println("=======" + i++ + "=======");
            System.out.println(pila);
            String item = pila.desapilar();
            System.out.println(item);
        }
    }
}
