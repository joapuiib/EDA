package tema3.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.EntradaHash;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class EstudiantsNoSuspesList {
    public static ListaConPI<String> aprovats(ListaConPI<EntradaHash<String, Integer>> notes){
        Map<String, Boolean> m = new TablaHash<>(notes.talla());

        for (notes.inicio(); !notes.esFin(); notes.siguiente()){
            EntradaHash<String, Integer> tupla = notes.recuperar();
            String nom = tupla.getClave();
            Integer nota  = tupla.getValor();

            Boolean aprovat = m.recuperar(nom);
            if(aprovat == null)
                aprovat = true;
            if(nota < 5)
                aprovat = false;

            m.insertar(nom, aprovat);
        }

        ListaConPI<String> aprovats = new LEGListaConPI<>();
        ListaConPI<String> claves = m.claves();
        for (claves.inicio(); !claves.esFin(); claves.siguiente()) {
            String nom = claves.recuperar();
            Boolean aprovat = m.recuperar(nom);
            if(aprovat)
                aprovats.insertar(nom);
        }
        return aprovats;
    }

    public static void main(String[] args) {
        ListaConPI<EntradaHash<String, Integer>> notes = new LEGListaConPI<>();
        // notes.insertar(new EntradaHash<>("Pere", 1));
        // notes.insertar(new EntradaHash<>("Pere", 4));
        notes.insertar(new EntradaHash<>("Maria", 6));
        notes.insertar(new EntradaHash<>("Pere", 9));
        notes.insertar(new EntradaHash<>("Maria", 5));
        notes.insertar(new EntradaHash<>("Pere", 6));

        System.out.println(aprovats(notes));
    }
}
