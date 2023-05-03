package tema5.implementacion;

public class ABB<E extends Comparable<E>>{
    protected NodoABB<E> raiz;

    public ABB(){
        raiz = null;
    }

    public boolean esVacio(){
        return raiz == null;
    }

    public int talla(){
        return talla(raiz);
    }
    protected int talla(NodoABB<E> actual){
        return actual == null ? 0 : actual.talla;
    }

    public E recuperar(E e){
        NodoABB<E> res = recuperar(e, raiz);
        return res == null ? null : res.dato;
    }
    protected NodoABB<E> recuperar(E e, NodoABB<E> actual){
        if(actual == null || actual.dato.equals(e)) {
            return actual;
        } else {
            int cmp = e.compareTo(actual.dato);
            if(cmp < 0)
                return recuperar(e, actual.izq);
            else
                return recuperar(e, actual.der);
        }
    }

    public void insertar(E e){
        raiz = insertar(e, raiz);
    }

    protected NodoABB<E> insertar(E e, NodoABB<E> actual){
        NodoABB<E> res = actual;
        if (actual != null) {
            int resC = actual.dato.compareTo(e);
            if (resC > 0) {
                res.izq = insertar(e, actual.izq);
            } else if (resC < 0) {
                res.der = insertar(e, actual.der);
            } else {
                res.dato = e;
            }
            res.talla = 1 + talla(res.izq) + talla(res.der);
        } else {
            res = new NodoABB<E>(e);
        }
        return res;
    }

    public void eliminar(E e){
        eliminar(e, raiz);
    }

    protected NodoABB<E> eliminar(E e, NodoABB<E> actual){
        NodoABB<E> res = actual;
        if (actual != null) {
            int resC = actual.dato.compareTo(e);
            if (resC > 0) {
                res.izq = eliminar(e, actual.izq);
            } else if (resC < 0) {
                res.der = eliminar(e, actual.der);
            } else {
                if (actual.izq == null) {
                    return actual.der;
                }
                else if (actual.der == null) {
                    return actual.izq;
                }
                else {
                    res.dato = recuperarMin(actual.der).dato;
                    res.der = eliminarMin(actual.der);
                }
            }
            res.talla = 1 + talla(res.izq) + talla(res.der);
        } else {
            res = new NodoABB<E>(e);
        }
        return res;
    }

    public E recuperarMin() {
        return recuperarMin(raiz).dato;
    }
    protected NodoABB<E> recuperarMin(NodoABB<E> actual) {
        if (actual.izq == null) { return actual; }
        return recuperarMin(actual.izq);
    }

    public E eliminarMin() {
        E res = recuperarMin();
        raiz = eliminarMin(raiz);
        return res;
    }
    protected NodoABB<E> eliminarMin(NodoABB<E> actual) {
        if (actual.izq == null) {
            return actual.der;
        }
        actual.izq = eliminarMin(actual.izq);
        actual.talla--;
        return actual;
    }
    public E sucesor(E e) {
        NodoABB<E> res = sucesor(e, raiz);
        if (res == null) { return null; }
        return res.dato;
    }
    protected NodoABB<E> sucesor(E e, NodoABB<E> actual) {
        NodoABB<E> res = null;
        if (actual != null) {
            int resC = actual.dato.compareTo(e);
            if (resC > 0) {
                res = sucesor(e, actual.izq);

                if (res == null) {
                    res = actual;
                }
            }
            else {
                res = sucesor(e, actual.der);
            }
        }
        return res;
    }

    public E seleccionar(int k) {
        return seleccionar(k, raiz).dato;
    }
    protected NodoABB<E> seleccionar(int k, NodoABB<E> actual) {
        int tallaIzq = talla(actual.izq);
        if(k == tallaIzq + 1){
            return actual;
        } else if (k <= tallaIzq) {
            return seleccionar(k, actual.izq);
        } else {
            return seleccionar(k - tallaIzq - 1, actual.der);
        }
    }
}
