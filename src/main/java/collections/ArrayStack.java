package collections;

public class ArrayStack<E> implements Stack<E> {
    /** Tamanyo por defecto de la pila */
    protected static final int DEFAULT_SIZE = 10;

    /** Array de elementos genéricos para almacenar los elementos de la cola */
    protected E[] array;

    /**
     * Índices de la cola para localizar los elementos de la cola.
     * first: Indica el primer elemento de la cola o siguiente elemento en salir.
     * last: Indica el último elemento de la cola o el últime elemento que ha entrado en la cola.
     * length: Indica el número de elementos presentes en la cola.
     */
    protected int length;

    @SuppressWarnings("unchecked")
    public ArrayStack(){
        array = (E[]) new Object[DEFAULT_SIZE];
        length = 0;
    }

    @SuppressWarnings("unchecked")
    protected void duplicateArray(){
        int newSize = array.length * 2;
        E[] newArray = (E[]) new Object[newSize];
        for (int i = 0; i < length; i++){
            newArray[i] = array[i];
        }
        array = newArray;
    }

    @Override
    public void push(E item) {
        if(length == array.length) duplicateArray();

        array[length++] = item;
    }

    @Override
    public E pop() {
        if(isEmpty())
            return null;

        int index = length - 1;
        E item = array[index];
        array[index] = null;
        length--;

        return item;
    }

    @Override
    public E first() {
        if(isEmpty())
            return null;

        return array[length - 1];
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 20; i++) {
            stack.push(i);
        }

        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }
}
