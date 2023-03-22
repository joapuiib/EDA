package tema3.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class Parcial2017CochesPorAnyoLista {
    public static Map<Integer, ListaConPI<String>> cochesPorAnyo(Map<String, Integer> dgt){
        Map<Integer, ListaConPI<String>> cochesPorAnyo = new TablaHash<>(dgt.talla());

        ListaConPI<String> claves = dgt.claves();
        for (claves.inicio(); !claves.esFin(); claves.siguiente()){
            String matricula = claves.recuperar();
            Integer anyo = dgt.recuperar(matricula);

            ListaConPI<String> cochesAnyo = cochesPorAnyo.recuperar(anyo);
            if(cochesAnyo == null) {
                cochesAnyo = new LEGListaConPI<>();
                cochesPorAnyo.insertar(anyo, cochesAnyo);
            }
            cochesAnyo.insertar(matricula);
        }
        return cochesPorAnyo;
    }

    public static void main(String[] args) {
        Map<String, Integer> dgt = new TablaHash<>(10);
        dgt.insertar("0000 BBB", 2000);
        dgt.insertar("0001 BBB", 2000);
        dgt.insertar("0002 BBB", 2000);
        dgt.insertar("0003 BBB", 2001);
        dgt.insertar("0004 BBB", 2001);
        dgt.insertar("0005 BBB", 2002);
        dgt.insertar("0006 BBB", 2003);
        dgt.insertar("0007 BBB", 2003);
        dgt.insertar("0008 BBB", 2003);
        dgt.insertar("0009 BBB", 2005);
        dgt.insertar("0010 BBB", 2007);

        Map<Integer, ListaConPI<String>> cochesPorAnyo = cochesPorAnyo(dgt);
        System.out.println(dgt);
        System.out.println(cochesPorAnyo);
    }
}