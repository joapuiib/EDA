package tema3.ejercicios;

import tema1.implementacion.array.ArrayPila;
import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema1.modelos.Pila;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class Simulacion2023TuplaSuma {
    public static String tuplas(ListaConPI<Integer> l1, ListaConPI<Integer> l2, Integer e){
        String res = "";
        Map<Integer, Integer> mapa = new TablaHash<>(l1.talla());
        for (l1.inicio(); !l1.esFin(); l1.siguiente()){
            Integer e1 = l1.recuperar();
            mapa.insertar(e - e1, e1);
        }
        for (l2.inicio(); !l2.esFin(); l2.siguiente()){
            Integer e2 = l2.recuperar();
            Integer e1 = mapa.recuperar(e2);
            if(e1 != null){
                res += (e1 + "+" + e2 + "=" + e + "\n");
            }
        }

        return res;
    }

    public static String tuplasProfessor(ListaConPI<Integer> l1, ListaConPI<Integer> l2, Integer e){
        String res = "";
        Map<Integer, Integer> mapa = new TablaHash<>(l1.talla());
        for (l1.inicio(); !l1.esFin(); l1.siguiente()){
            Integer e1 = l1.recuperar();
            Integer l1aux = mapa.recuperar(e1);
            for (l2.inicio(); !l2.esFin(); l2.siguiente()){
                Integer e2 = l1.recuperar();
                Integer l2aux = mapa.recuperar(e2);
                if(e1 + e2 > 10)
                    break;
                if(l1aux == null && l2aux == null && e1 + e2 == e){
                    mapa.insertar(e1, e2);
                    mapa.insertar(e2, e1);
                    res += (e1 + "+" + e2 + "=" + e + "\n");
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ListaConPI<Integer> l1 = new LEGListaConPI<>();
        l1.insertar(1);
        l1.insertar(4);
        l1.insertar(6);
        l1.insertar(9);
        ListaConPI<Integer> l2 = new LEGListaConPI<>();
        l2.insertar(1);
        l2.insertar(3);
        l2.insertar(4);
        l2.insertar(5);

        System.out.println(tuplas(l1, l2, 10));
    }
}
