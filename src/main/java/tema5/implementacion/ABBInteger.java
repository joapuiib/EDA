package tema5.implementacion;

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
}