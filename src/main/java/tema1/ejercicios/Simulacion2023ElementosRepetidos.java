package tema1.ejercicios;

import tema1.implementacion.array.ArrayPila;
import tema1.modelos.ListaConPI;
import tema1.modelos.Pila;

public class Simulacion2023ElementosRepetidos {
    public static <E> Pila<E> borrarRepetidos(ListaConPI<E> l){
        Pila<E> pila = new ArrayPila<>();

        E anterior = null;
        for(l.inicio(); !l.esFin(); l.siguiente()){
            E e = l.recuperar();
            if(e.equals(anterior)){
                l.eliminar();
                pila.apilar(e);
            } else {
                anterior = e;
            }
        }

        return pila;
    }

    public static <E> Pila<E> borrarRepetidosHuecos(ListaConPI<E> l){
        Pila<E> p = new ArrayPila<>()/* hueco 1 <E>()*/;
        if(l.esVacia() /* hueco 2 () */)
            return p;
        l.inicio();/* hueco 3 ();*/
        E e1 = l.recuperar()/* hueco 4 ()*/;
        do {
            l.siguiente(); /* hueco 5 ();*/
            if(!l.esFin() /* hueco 6 () */){
                E e2 = l.recuperar()/* hueco 7 ()*/;
                if(e1.equals(e2) /* hueco 8 */){
                    p.apilar(e2);/* hueco 9 (e2);*/
                    l.eliminar();/* hueco 10 ();*/
                }
                e1 = e2;
            }
        } while (!l.esFin() /* hueco 11 () */);
        return p;
    }
}
