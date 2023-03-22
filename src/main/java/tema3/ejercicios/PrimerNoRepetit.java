package tema3.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class PrimerNoRepetit {
    public static <E> E primerNoRepetit(ListaConPI<E> l1) {
        Map<E, Integer> mapa = new TablaHash<>(l1.talla());

        for (l1.inicio(); !l1.esFin(); l1.siguiente()){
            E e = l1.recuperar();
            Integer comptador = mapa.recuperar(e);

            if(comptador == null)
                comptador = 1;
            else
                comptador++;

            mapa.insertar(e, comptador);
        }

        for (l1.inicio(); !l1.esFin(); l1.siguiente()) {
            E e = l1.recuperar();
            Integer comptador = mapa.recuperar(e);

            if(comptador == 1)
                return e;
        }
        return null;
    }

    public static void main(String[] args) {
        ListaConPI<Integer> l1 = new LEGListaConPI<>();
        l1.insertar(1);
        l1.insertar(5);
        l1.insertar(3);
        l1.insertar(2);
        l1.insertar(2);
        l1.insertar(3);
        l1.insertar(5);
        l1.insertar(4);
        l1.insertar(1);
        l1.insertar(3);
        l1.insertar(2);

        System.out.println(primerNoRepetit(l1));
    }
}