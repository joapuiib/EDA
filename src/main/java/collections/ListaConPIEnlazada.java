package collections;

public class ListaConPIEnlazada<E> implements ListaConPI<E> {

    Node<E> primero, anterior, ultimo;
    int talla;

    public ListaConPIEnlazada(){
        primero = anterior = ultimo = new Node<>(null);
        talla = 0;
    }

    @Override
    public void insertar(E e) {
        Node<E> nuevo = new Node<>(e);
        talla++;

        nuevo.siguiente = anterior.siguiente;
        anterior.siguiente = nuevo;

        if(anterior == ultimo)
            ultimo = nuevo;

        anterior = nuevo;
    }

    @Override
    public void eliminar() {
        if(esFin())
            return;

        talla--;
        if(anterior.siguiente == ultimo){
            ultimo = anterior;
        }
        anterior.siguiente = anterior.siguiente.siguiente;
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
        // return talla == 0;
        return anterior == primero;
    }

    @Override
    public int talla() {
        return 0;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("[");

        Node<E> aux = primero.siguiente;
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
