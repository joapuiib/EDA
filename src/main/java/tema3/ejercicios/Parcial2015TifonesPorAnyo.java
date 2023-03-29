package tema3.ejercicios;

import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class Parcial2015TifonesPorAnyo {
    public static Map<Integer, Integer> tifonesPorAnyo(Map<String, Integer> tifones){
        Map<Integer, Integer> m = new TablaHash<>(tifones.talla());
        // Anyo => Número de tifones en ese anyo

        ListaConPI<String> claves = tifones.claves();
        for (claves.inicio(); !claves.esFin(); claves.siguiente()){
            String nombre = claves.recuperar();
            Integer anyo = tifones.recuperar(nombre);

            Integer cont = m.recuperar(anyo);
            if(cont == null)
                cont = 1;
            else
                cont++;
            m.insertar(anyo, cont);
        }

        return m;
    }

    public static void main(String[] args) {
        Map<String, Integer> tifones = new TablaHash<>(10);
        tifones.insertar("Maria", 2000);
        tifones.insertar("Pere", 2000);
        tifones.insertar("Joan", 2000);
        tifones.insertar("Carles", 2001);
        tifones.insertar("Ana", 2001);
        tifones.insertar("Paula", 2002);
        tifones.insertar("Adrià", 2003);
        tifones.insertar("Mar", 2003);
        tifones.insertar("Marc", 2003);
        tifones.insertar("Josep", 2005);
        tifones.insertar("Arnau", 2007);

        Map<Integer, Integer> tifonesPorAnyo = tifonesPorAnyo(tifones);
        System.out.println(tifonesPorAnyo);
    }
}