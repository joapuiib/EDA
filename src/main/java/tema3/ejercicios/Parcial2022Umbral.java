package tema3.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class Parcial2022Umbral {
    public static ListaConPI<String> superanUmbral(ListaConPI<String> telefonos, int umbral){
        ListaConPI<String> superanUmbral = new LEGListaConPI<>();
        Map<String, Integer> m = new TablaHash<>(telefonos.talla());

        for (telefonos.inicio(); !telefonos.esFin(); telefonos.siguiente()){
            String telefono = telefonos.recuperar();
            Integer frec = m.recuperar(telefono);

            if(frec != null){
                frec++;
            } else {
                frec = 1;
            }
            m.insertar(telefono, frec);

            if(frec == umbral)
                superanUmbral.insertar(telefono);
        }

        return superanUmbral;
    }

    public static void main(String[] args) {
        ListaConPI<String> telefonos = new LEGListaConPI<>();
        telefonos.insertar("A");
        telefonos.insertar("B");
        telefonos.insertar("C");
        telefonos.insertar("D");
        telefonos.insertar("C");
        telefonos.insertar("A");
        telefonos.insertar("C");
        telefonos.insertar("B");
        telefonos.insertar("D");
        telefonos.insertar("C");
        telefonos.insertar("A");
        telefonos.insertar("E");
        telefonos.insertar("F");
        telefonos.insertar("C");

        ListaConPI<String> superan = superanUmbral(telefonos, 3);
        System.out.println(superan);
    }
}
