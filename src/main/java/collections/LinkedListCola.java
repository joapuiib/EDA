package collections;

import collections.modelos.Cola;

public class LinkedListCola<E> implements Cola<E> {
    private Nodo<E> first;
    private Nodo<E> last;
    private int length;


    public LinkedListCola() {
        first = null;
        last = null;
        length = 0;
    }

    @Override
    public void encolar(E item) {
        Nodo<E> nodo = new Nodo<>(item);
        if (esVacia()){
            first = nodo;
            last = nodo;
        } else {
            last.setSiguiente(nodo);
            last = nodo;
        }
        length++;
    }

    public void queue(E item, int index) {
        Nodo<E> nodo = new Nodo<>(item);
        if (index >= length){
            encolar(item);
        } else {
            Nodo<E> anterior = null;
            Nodo<E> seguent = first;
            for (int i = 1; i <= index; i++){
                anterior = seguent;
                seguent = seguent.next();
            }

            if(anterior != null)
                anterior.setSiguiente(nodo);

            nodo.setSiguiente(seguent);
            length++;
        }
    }

    @Override
    public E desencolar() {
        if(esVacia())
            return null;

        E object = first.getObjecto();

        this.first = first.next();
        length--;

        return object;
    }

    @Override
    public E primero() {
        return this.first.getObjecto();
    }

    @Override
    public boolean esVacia() {
        // return first == null;
        return length == 0;
    }

    public static void main(String[] args) {
        LinkedListCola<String> linkedListQueue = new LinkedListCola<>();
        linkedListQueue.encolar("Item1");
        linkedListQueue.encolar("Item2");
        linkedListQueue.encolar("Item3");
        linkedListQueue.encolar("Item4");
        linkedListQueue.queue("Item5", 2);
        linkedListQueue.queue("Item6", 100);

        while (!linkedListQueue.esVacia()) {
            String item = linkedListQueue.desencolar();
            System.out.println(item);
        }
    }
}
