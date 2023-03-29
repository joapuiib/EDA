package tema3.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class Parcial2014ModaLista {
    public static <E> E moda(ListaConPI<E> l){
        Map<E, Integer> m = new TablaHash<>(l.talla());

        E vMax = null;
        int frecMax = 0;
        for (l.inicio(); !l.esFin() ;l.siguiente()){
            E e = l.recuperar();
            Integer frec = m.recuperar(e);
            if(frec == null)
                frec = 1;
            else
                frec++;
            m.insertar(e, frec);

            if(frec > frecMax){
                frecMax = frec;
                vMax = e;
            }
        }

        return vMax;
    }

    public static void main(String[] args) {
        ListaConPI<String> l = new LEGListaConPI<>();
    }
}