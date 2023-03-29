package tema3.ejercicios;

import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

import java.util.Arrays;

public class Rec2015NoRepetidos {
    public static String[] noRepetidos(String[] v1, String[] v2){
        Map<String, Boolean> m = new TablaHash<>(v1.length);
        for(String s1 : v1){
            m.insertar(s1, false);
        }

        int talla = 0;
        for(String s2 : v2){
            Boolean found = m.recuperar(s2);
            if(found != null && !found){
                m.insertar(s2, true);
                talla++;
            }
        }

        int i = 0;
        String[] noRepetidos = new String[talla];
        ListaConPI<String> claves = m.claves();
        for(claves.inicio(); !claves.esFin(); claves.siguiente()){
            String s = claves.recuperar();
            Boolean found = m.recuperar(s);
            if(!found){
                noRepetidos[i++] = s;
            }
        }

        return noRepetidos;
    }

    public static String[] noRepetidosSolucio(String[] v1, String[] v2){
        Map<String, Boolean> m = new TablaHash<>(v2.length);
        for(String s1 : v1){
            m.insertar(s1, true);
        }
        for(String s2 : v2){
            m.eliminar(s2);
        }

        String[] noRepetidos = new String[m.talla()];
        ListaConPI<String> claves = m.claves();
        int i = 0;
        for(claves.inicio(); !claves.esFin(); claves.siguiente()){
            String s = claves.recuperar();
            noRepetidos[i++] = s;
        }

        return noRepetidos;
    }

    public static void main(String[] args) {
        String[] l1 = new String[]{"A", "B", "C", "D"};
        String[] l2 = new String[]{"B", "D", "E"};
        System.out.println(Arrays.toString(noRepetidos(l1, l2)));
    }
}