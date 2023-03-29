package tema3.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class EstudiantsNoSuspesMap {
    public static ListaConPI<String> aprovats(Map<String, ListaConPI<Integer>> expedients){
        ListaConPI<String> aprovats = new LEGListaConPI<>();

        ListaConPI<String> claves = expedients.claves();
        for (claves.inicio(); !claves.esFin(); claves.siguiente()){
            String nom = claves.recuperar();
            ListaConPI<Integer> notes = expedients.recuperar(nom);

            boolean aprovat = true;
            for (notes.inicio(); aprovat && !notes.esFin(); notes.siguiente()){
                Integer nota = notes.recuperar();
                if(nota < 5)
                    aprovat = false;
            }

            if(aprovat)
                aprovats.insertar(nom);
        }

        return aprovats;
    }

    public static void main(String[] args) {
        ListaConPI<Integer> l1 = new LEGListaConPI<>();
        l1.insertar(1);
        l1.insertar(4);
        l1.insertar(6);
        l1.insertar(9);
        ListaConPI<Integer> l2 = new LEGListaConPI<>();
        l2.insertar(5);
        l2.insertar(6);

        Map<String, ListaConPI<Integer>> expedients = new TablaHash<>(10);
        expedients.insertar("Pere", l1);
        expedients.insertar("Maria", l2);

        System.out.println(aprovats(expedients));
    }
}
