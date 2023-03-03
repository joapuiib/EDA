package collections;

public interface Stack<E> {
    void push(E item);

    E pop();

    E first();

    boolean isEmpty();
}
