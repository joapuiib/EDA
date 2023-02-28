package collections;

public class ArrayQueue<E> implements Queue<E> {
    /** Tamanyo por defecto de la cola */
    protected static final int DEFAULT_SIZE = 10;

    /** Array de elementos genéricos para almacenar los elementos de la cola */
    protected E[] array;

    /**
     * Índices de la cola para localizar los elementos de la cola.
     * first: Indica el primer elemento de la cola o siguiente elemento en salir.
     * last: Indica el último elemento de la cola o el últime elemento que ha entrado en la cola.
     * length: Indica el número de elementos presentes en la cola.
     */
    protected int first, last, length;

    @SuppressWarnings("unchecked")
    public ArrayQueue(){
        array = (E[]) new Object[DEFAULT_SIZE];
        first = last = length = 0;
    }

    @SuppressWarnings("unchecked")
    protected void duplicateArray(){
        int newSize = array.length * 2;
        E[] newArray = (E[]) new Object[newSize];
        for (int i = 0; i <= length; i++, first = incrementIndex(first)){
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
    public void queue(E item) {
        if(length == array.length) duplicateArray();

        array[last] = item;

        last = incrementIndex(last);
        length++;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            return null;

        E item = array[first];

        first = incrementIndex(first);
        length--;

        return item;
    }

    @Override
    public E first() {
        if(isEmpty())
            return null;

        return array[first];
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }
}
