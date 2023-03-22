package tema1.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;

public class ComprovarIgualesListaPI {
    public static <E> boolean sonIguales(ListaConPI<E> l1, ListaConPI<E> l2){
        if(l1.talla() != l2.talla())
            return false;

        l1.inicio();
        l2.inicio();

        while (!l1.esFin() && !l2.esFin()){
            E e1 = l1.recuperar();
            E e2 = l2.recuperar();
            if(!e1.equals(e2))
                return false;

            l1.siguiente();
            l2.siguiente();
        }
        return true;
    }
}
