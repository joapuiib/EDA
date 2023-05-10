package tema5.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema5.implementacion.ABBMapOrdenado;
import tema5.implementacion.EntradaMap;
import tema5.modelos.MapOrdenado;

public class teoriaProblema2 {
    public static <E extends Comparable<E>> void mapSort(E[] v){
        MapOrdenado<E, Integer> mapOrdenado = new ABBMapOrdenado<>();
        for (int i = 0; i < v.length; i++) {
            mapOrdenado.insertar(v[i], null);
        }

        v[0] = mapOrdenado.recuperarMin();
        for (int i = 1; i < v.length; i++) {
            v[i] = mapOrdenado.sucesor(v[i - 1]);
        }
    }
}