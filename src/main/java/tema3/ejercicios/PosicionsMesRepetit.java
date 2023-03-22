package tema3.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class PosicionsMesRepetit {
    public static <E> ListaConPI<Integer> posicionsMesRepetit(ListaConPI<E> l1) {
        Map<E, ListaConPI<Integer>> mapa = new TablaHash<>(l1.talla());
        ListaConPI<Integer> max = null;

        int i = 0;
        for (l1.inicio(); !l1.esFin(); l1.siguiente()){
            E e = l1.recuperar();

            ListaConPI<Integer> posicions = mapa.recuperar(e);
            if(posicions == null) {
                posicions = new LEGListaConPI<>();
                mapa.insertar(e, posicions);
            }
            posicions.insertar(i++);

            if(max == null || posicions.talla() > max.talla()){
                max = posicions;
            }
        }

        return max;
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
        l1.insertar(3);
        l1.insertar(2);

        System.out.println(posicionsMesRepetit(l1));

        l1.insertar(2);
        System.out.println(posicionsMesRepetit(l1));
    }
}