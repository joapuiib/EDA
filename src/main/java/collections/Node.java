package collections;

public class Node<E> {
    private E object;
    private Node<E> next;

    public Node(E object) {
        this.object = object;
        this.next = null;
    }

    public E getObject(){
        return this.object;
    }

    public Node<E> next(){
        return this.next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
}
