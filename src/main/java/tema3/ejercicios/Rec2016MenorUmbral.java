package tema3.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

import java.util.Objects;

public class Rec2016MenorUmbral {
    public static <E> ListaConPI<E> menorUmbral(E[] v, int u){
        Map<E, Integer> map = new TablaHash<>(v.length);
        for (int i = 0; i < v.length; i++) {
            E e = v[i];
            Integer frec = map.recuperar(e);
            if(frec == null)
                frec = 1;
            else
                frec++;
            map.insertar(e, frec);
        }

        ListaConPI<E> resultat = new LEGListaConPI<>();
        ListaConPI<E> claves = map.claves();
        for(claves.inicio(); !claves.esFin(); claves.siguiente()){
            E clave = claves.recuperar();
            Integer frec = map.recuperar(clave);
            if (frec < u)
                resultat.insertar(clave);
        }
        return resultat;
    }
}