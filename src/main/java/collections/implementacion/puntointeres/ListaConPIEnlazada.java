package collections.implementacion.puntointeres;

import collections.modelos.ListaConPI;

public class ListaConPIEnlazada<E> implements ListaConPI<E> {

    Nodo<E> primero, anterior, ultimo;
    int talla;

    public ListaConPIEnlazada(){
        primero = anterior = ultimo = new Nodo<>(null);
        talla = 0;
    }

    @Override
    public void insertar(E e) {
        Nodo<E> nuevo = new Nodo<>(e);
        talla++;

        nuevo.siguiente = anterior.siguiente;
        anterior.siguiente = nuevo;

        if(anterior == ultimo)
            ultimo = nuevo;

        anterior = nuevo;
    }

    @Override
    public E eliminar() {
        if(esFin()) {
            return null;
        } else {
            E objecto = recuperar();

            talla--;
            if (anterior.siguiente == ultimo) {
                ultimo = anterior;
            }
            anterior.siguiente = anterior.siguiente.siguiente;

            return objecto;
        }
    }

    @Override
    public E recuperar() {
        return anterior.siguiente.objecto;
    }

    @Override
    public void inicio() {
        anterior = primero;
    }

    @Override
    public void siguiente() {
        if(!esFin())
            anterior = anterior.siguiente;
    }

    @Override
    public void fin() {
        anterior = ultimo;
    }


    @Override
    public boolean esFin() {
        return anterior == ultimo;
    }

    @Override
    public boolean esVacia() {
        return primero == ultimo;
    }

    @Override
    public int talla() {
        return talla;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("[");

        Nodo<E> aux = primero.siguiente;
        while (aux != null){
            s.append(aux.objecto);
            if(aux != ultimo)
                s.append(", ");
            aux = aux.siguiente;
        }

        s.append("]");
        return s.toString();
    }

    public static void main(String[] args) {
        ListaConPIEnlazada<Integer> lista = new ListaConPIEnlazada<>();
        lista.insertar(1);
        lista.insertar(2);
        lista.insertar(3);

        lista.inicio();
        lista.insertar(4);

        lista.siguiente();
        lista.insertar(5);

        lista.fin();
        lista.insertar(6);

        lista.inicio();
        lista.siguiente();
        lista.siguiente();
        lista.siguiente();
        lista.siguiente();
        lista.siguiente();
        lista.eliminar();

        System.out.println(lista);
    }
}
