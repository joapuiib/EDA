package collections.implementacion.puntointeres;

public class Nodo<E> {
    E objecto;
    Nodo<E> siguiente;

    public Nodo(E object) {
        this.objecto = object;
        this.siguiente = null;
    }

    public E getObjecto(){
        return this.objecto;
    }

    public Nodo<E> next(){
        return this.siguiente;
    }

    public void setSiguiente(Nodo<E> siguiente) {
        this.siguiente = siguiente;
    }
}
