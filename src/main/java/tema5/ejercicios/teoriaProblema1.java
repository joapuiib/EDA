package tema5.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema5.implementacion.EntradaMap;
import tema5.modelos.MapOrdenado;

public class teoriaProblema1 {
    public static <C extends Comparable<C>, V> ListaConPI<EntradaMap<C, V>> entradas(MapOrdenado<C, V> mo){
        ListaConPI<EntradaMap<C, V>> l = new LEGListaConPI<>();

        EntradaMap<C, V> min = mo.recuperarEntradaMin();

        while(min != null){
            l.insertar(min);
            min = mo.sucesorEntrada(min.getClave());
        }

        return l;
    }
}