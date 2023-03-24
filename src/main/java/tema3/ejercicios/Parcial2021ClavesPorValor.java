package tema3.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class Parcial2021ClavesPorValor {

    public static <C, V> Map<V, ListaConPI<C>> clavesPorValor(Map<C, V> m1){
        Map<V, ListaConPI<C>> m2 = new TablaHash<>(m1.talla());

        ListaConPI<C> claves = m1.claves();
        for (claves.inicio(); !claves.esFin(); claves.siguiente()){
            C clave = claves.recuperar();
            V valor = m1.recuperar(clave);

            ListaConPI<C> listaClaves = m2.recuperar(valor);
            if(listaClaves == null){
                listaClaves = new LEGListaConPI<>();
                m2.insertar(valor, listaClaves);
            }

            listaClaves.insertar(clave);
        }
        return m2;
    }


    public static void main(String[] args) {
        Map<String, Integer> puntos = new TablaHash<>(10);
        puntos.insertar("Larry Bird", 258);
        puntos.insertar("Micheal Jordan", 362);
        puntos.insertar("Magic Johnson", 264);
        puntos.insertar("Patrick Ewing", 258);
        puntos.insertar("Shaquille O'Neal", 264);
        puntos.insertar("David Robinson", 243);
        puntos.insertar("Pau Gasol", 264);


        Map<Integer, ListaConPI<String>> masFrecuente = clavesPorValor(puntos);
        System.out.println(masFrecuente);
    }
}
