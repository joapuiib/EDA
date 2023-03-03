package collections;

public class Node<E> {
    E objecto;
    Node<E> siguiente;

    public Node(E object) {
        this.objecto = object;
        this.siguiente = null;
    }

    public E getObjecto(){
        return this.objecto;
    }

    public Node<E> next(){
        return this.siguiente;
    }

    public void setSiguiente(Node<E> siguiente) {
        this.siguiente = siguiente;
    }
}
