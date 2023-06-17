package tema5.implementacion;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;

public class ABBInteger extends ABB<Integer> {
    public void cambiarSigno(){
        cambiarSigno(raiz);
    }

    protected void cambiarSigno(NodoABB<Integer> actual){
        if(actual != null){
            actual.dato = -actual.dato;

            NodoABB<Integer> aux = actual.izq;
            actual.izq = actual.der;
            actual.der = aux;

            cambiarSigno(actual.izq);
            cambiarSigno(actual.der);
        }
    }

    public ListaConPI<Integer> numerosMayores(int n){
        ListaConPI<Integer> l = new LEGListaConPI<>();
        numerosMayores(raiz, n, l);
        return l;
    }

    /**
     * Recorrer en IN-ORDER para insertar en orden ascendente
     */
    protected void numerosMayores(NodoABB<Integer> actual, int n, ListaConPI<Integer> l){
        if (actual == null)
            return;

        int cmp = actual.dato.compareTo(n);
        if (cmp <= 0){
            numerosMayores(actual.der, n, l);
        } else {
            // L - N - R
            numerosMayores(actual.izq, n, l);
            l.insertar(actual.dato);
            numerosMayores(actual.der, n, l);
        }
    }
}