package collections.implementacion.array;

import collections.modelos.Cola;

public class ArrayCola<E> implements Cola<E> {
    /** Tamanyo por defecto de la cola */
    protected static final int DEFAULT_SIZE = 10;

    /** Array de elementos genéricos para almacenar los elementos de la cola */
    protected E[] array;

    /**
     * Índices de la cola para localizar los elementos de la cola.
     * length: Indica el número de elementos presentes en la cola.
     */
    protected int first, last, length;

    @SuppressWarnings("unchecked")
    public ArrayCola(){
        array = (E[]) new Object[DEFAULT_SIZE];
        first = last = length = 0;
    }

    @SuppressWarnings("unchecked")
    protected void duplicateArray(){
        int newSize = array.length * 2;
        E[] newArray = (E[]) new Object[newSize];
        for (int i = 0; i < length; i++, first = incrementIndex(first)){
            newArray[i] = array[first];
        }
        array = newArray;
        first = 0;
        last = length;
    }

    protected int incrementIndex(int index){
        index++;
        if (index >= array.length)
            index = 0;

        return index;
    }

    @Override
    public void encolar(E item) {
        if(length == array.length) duplicateArray();

        array[last] = item;

        last = incrementIndex(last);
        length++;
    }

    @Override
    public E desencolar() {
        if(esVacia())
            return null;

        E item = array[first];
        array[first] = null;

        first = incrementIndex(first);
        length--;

        return item;
    }

    @Override
    public E primero() {
        if(esVacia())
            return null;

        return array[first];
    }

    @Override
    public boolean esVacia() {
        return length == 0;
    }
}
