package tema3.ejemplo;

import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class EjemploTablaHash {
    public static void main(String[] args) {
        Map<String, Integer> map = new TablaHash<>(3);

        map.insertar("Elena", 1234);
        map.insertar("Joan", 1111);
        map.insertar("Pedro", 4567);
        map.insertar("Javier", 4567);
        map.insertar("Marta", 2222);
        map.insertar("Dani", 3333);

        map.insertar("Joan", 3333);

        System.out.println("Elena: " + map.recuperar("Elena"));
        System.out.println("Joan: " + map.recuperar("Joan"));
        System.out.println("Pedro: " + map.recuperar("Pedro"));

        ListaConPI<String> claves = map.claves();
        System.out.println(claves);
    }
}
