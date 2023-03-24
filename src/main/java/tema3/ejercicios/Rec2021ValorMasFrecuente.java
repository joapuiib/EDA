package tema3.ejercicios;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema3.implementacion.TablaHash;
import tema3.modelos.Map;

public class Rec2021ValorMasFrecuente {
    public static <C, V> V valorMasFrecuente(Map<C, V> map){
        Map<V, Integer> frecuencias = new TablaHash<>(map.talla());
        V valorMax = null;
        int frecuenciaMaxima = 0;

        ListaConPI<C> claves = map.claves();
        for (claves.inicio(); !claves.esFin(); claves.siguiente()){
            C clave = claves.recuperar();
            V valor = map.recuperar(clave);

            Integer frec = frecuencias.recuperar(valor);
            if(frec == null)
                frec = 1;
            else
                frec++;
            frecuencias.insertar(valor, frec);

            if(frec > frecuenciaMaxima){
                frecuenciaMaxima = frec;
                valorMax = valor;
            }
        }
        return valorMax;
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


        Integer masFrecuente = valorMasFrecuente(puntos);
        System.out.println(masFrecuente);
    }
}
