package tema5.implementacion;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.implementacion.puntointeres.Nodo;
import tema1.modelos.ListaConPI;

import java.util.NoSuchElementException;

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

    public E insertar(E e){
        NodoABB<E> old = new NodoABB<>(null);
        raiz = insertar(e, raiz, old);
        return old.dato;
    }

    protected NodoABB<E> insertar(E e, NodoABB<E> actual, NodoABB<E> old){
        NodoABB<E> res = actual;
        if (actual != null) {
            int resC = e.compareTo(actual.dato);
            if (resC < 0) {
                res.izq = insertar(e, actual.izq, old);
            } else if (resC > 0) {
                res.der = insertar(e, actual.der, old);
            } else {
                old.dato = res.dato;
                res.dato = e;
            }
            res.talla = 1 + talla(res.izq) + talla(res.der);
        } else {
            res = new NodoABB<E>(e);
        }
        return res;
    }

    public E eliminar(E e){
        NodoABB<E> old = new NodoABB<>(null);
        eliminar(e, raiz, old);
        return old.dato;
    }

    protected NodoABB<E> eliminar(E e, NodoABB<E> actual, NodoABB<E> old){
        NodoABB<E> res = actual;
        if (actual != null) {
            int resC = e.compareTo(actual.dato);
            if (resC < 0) {
                res.izq = eliminar(e, actual.izq, old);
            } else if (resC > 0) {
                res.der = eliminar(e, actual.der, old);
            } else {
                old.dato = actual.dato;
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

    public E recuperarMax(){
        NodoABB<E> max = recuperarMax(raiz);
        return max == null ? null : max.dato;
    }

    protected NodoABB<E> recuperarMax(NodoABB<E> actual){
        if(actual.der == null)
            return actual;
        else
            return recuperarMax(actual.der);
    }

    public E eliminarMax(){
        E old = recuperarMax();
        raiz = eliminarMax(raiz);
        return old;
    }
    protected NodoABB<E> eliminarMax(NodoABB<E> actual){
        if (actual.der == null) {
            return actual.izq;
        }
        actual.der = eliminarMax(actual.der);
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

    public E predecesor(E e) {
        NodoABB<E> res = predecesor(e, raiz);
        if (res == null) { return null; }
        return res.dato;
    }
    protected NodoABB<E> predecesor(E e, NodoABB<E> actual) {
        NodoABB<E> res = null;
        if (actual != null) {
            int resC = e.compareTo(actual.dato);
            if(resC <= 0)
                res = predecesor(e, actual.izq);
            else {
                res = predecesor(e, actual.der);

                if(res == null)
                    res = actual;
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

    public int enQueNivel(E e){
        return enQueNivel(e, raiz, 0);
    }
    protected int enQueNivel(E e, NodoABB<E> actual, int nivel){
        if (actual == null)
            return -1;
        int cmp = e.compareTo(actual.dato);
        if (cmp == 0)
            return nivel;
        else if (cmp > 0)
            return enQueNivel(e, actual.der, nivel + 1);
        else
            return enQueNivel(e, actual.izq, nivel + 1);
    }

    public int mayoresQue(E e){
        return mayoresQue(e, raiz);
    }
    protected int mayoresQue(E e, NodoABB<E> actual){
        if (actual == null)
            return 0;
        int res = 0;
        int cmp = e.compareTo(actual.dato);
        if (cmp == 0){
            res += talla(actual.der);
        } else if(cmp < 0) {
            res += 1 + mayoresQue(e, actual.izq);
            res += talla(actual.der);
        } else {
            res += mayoresQue(e, actual.der);
        }

        return res;
    }

    public String toStringInOrder(){
        StringBuilder res = new StringBuilder();
        res.append("[");
        res.append(toStringInOrder(raiz));
        res.append("]");
        return res.toString();
    }
    protected String toStringInOrder(NodoABB<E> actual){
        if (actual == null)
            return "";
        StringBuilder res = new StringBuilder();
        if (actual.izq != null){
            res.append(toStringInOrder(actual.izq));
            res.append(", ");
        }
        res.append(actual.dato.toString());
        if (actual.der != null){
            res.append(", ");
            res.append(toStringInOrder(actual.der));
        }
        return res.toString();
    }

    public ListaConPI<E> inOrder(){
        ListaConPI<E> l = new LEGListaConPI<>();
        inOrder(raiz, l);
        return l;
    }

    protected void inOrder(NodoABB<E> actual, ListaConPI<E> l){
        if(actual != null){
            inOrder(actual.izq, l);
            l.insertar(actual.dato);
            inOrder(actual.der, l);
        }
    }

    public E padreDe(E e){
        NodoABB<E> padre = padreDe(e, raiz);
        return padre == null ? null : padre.dato;
    }
    protected NodoABB<E> padreDe(E e, NodoABB<E> actual){
        if (actual == null)
            return null;
        int cmp = e.compareTo(actual.dato);
        if (cmp == 0)
            return null;
        else if (cmp < 0){
            if(actual.izq != null && actual.izq.dato.compareTo(e) == 0)
                return actual;
            else
                return padreDe(e, actual.izq);
        } else {
            if(actual.der != null && actual.der.dato.compareTo(e) == 0)
                return actual;
            else
                return padreDe(e, actual.der);
        }
    }

    public E hermanoDe(E e){
        NodoABB<E> padre = hermanoDe(e, raiz);
        return padre == null ? null : padre.dato;
    }
    protected NodoABB<E> hermanoDe(E e, NodoABB<E> actual){
        if (actual == null)
            return null;
        int cmp = e.compareTo(actual.dato);
        if (cmp == 0)
            return null;
        else if (cmp < 0){
            if(actual.izq != null && actual.izq.dato.compareTo(e) == 0)
                return actual.der;
            else
                return hermanoDe(e, actual.izq);
        } else {
            if(actual.der != null && actual.der.dato.compareTo(e) == 0)
                return actual.izq;
            else
                return hermanoDe(e, actual.der);
        }
    }

    public String datosEnNivel(int nivel){
        return "[" + datosEnNivel(nivel, raiz) + "]";
    }

    protected String datosEnNivel(int nivel, NodoABB<E> actual){
        if(actual == null)
            return "";

        String res = "";
        if(nivel == 0)
            res =  actual.dato.toString();
        else {
            String izq = datosEnNivel(nivel - 1, actual.izq);
            String der = datosEnNivel(nivel - 1, actual.der);
            if(!izq.isEmpty() && !der.isEmpty())
                res = izq + ", " + der;
            else if(!izq.isEmpty())
                res = izq;
            else if(!der.isEmpty())
                res = der;
        }
        return res;
    }

    public int alturaDeEquilibrado(){
        return alturaDeEquilibrado(raiz);
    }

    protected int alturaDeEquilibrado(NodoABB<E> actual){
        if(actual == null)
            return -1;

        int alturaIzq = alturaDeEquilibrado(actual.izq);
        int alturaDer = alturaDeEquilibrado(actual.der);
        if(Math.abs(alturaIzq - alturaDer) > 1)
            throw new NoSuchElementException();
        else
            return 1 + Math.max(alturaIzq, alturaDer);
    }
}
