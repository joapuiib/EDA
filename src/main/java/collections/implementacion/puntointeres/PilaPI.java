package collections.implementacion.puntointeres;

import collections.modelos.Pila;

public class PilaPI<E> extends ListaConPIEnlazada<E> implements Pila<E> {

    @Override
    public void push(E item) {
        inicio();
        insertar(item);
    }

    @Override
    public E pop() {
        inicio();
        return eliminar();
    }

    @Override
    public E first() {
        inicio();
        return recuperar();
    }

    public static void main(String[] args) {
        PilaPI<String> pila = new PilaPI<>();
        pila.push("Item1");
        pila.push("Item2");
        pila.push("Item3");
        pila.push("Item4");

        int i = 0;
        while (!pila.esVacia()) {
            System.out.println("=======" + i++ + "=======");
            System.out.println(pila);
            String item = pila.pop();
            System.out.println(item);
        }
    }
}
