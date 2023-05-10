package tema5.implementacion;

public class NodoABB<E> {
    protected E dato;
    protected NodoABB<E> izq, der;
    protected int talla;

    NodoABB(E e){
        dato = e;
        talla = 1;
        izq = der = null;
    }

    @Override
    public String toString(){
        return String.format("%s<%s, %s>", dato.toString(), izq == null ? "null" : izq.dato, der == null ? "null" : der.dato);
    }
}
