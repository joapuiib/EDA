package tema1.ejercicios;

import tema1.implementacion.puntointeres.LEGPila;
import tema1.modelos.Pila;

public class BorrarMenoresPila {
    public static <E extends Comparable<E>> int borrarMenor(Pila<E> pila, E dato){
        if(pila.esVacia())
            return 0;

        E objeto = pila.desapilar();

        int elementosBorrados = borrarMenor(pila, dato);

        if(objeto.compareTo(dato) >= 0){
            pila.apilar(objeto);
        } else {
            elementosBorrados++;
        }

        return elementosBorrados;
    }

    public static void main(String[] args) {
        Pila<Integer> pila = new LEGPila<>();
        pila.apilar(3);
        pila.apilar(4);
        pila.apilar(2);
        pila.apilar(5);
        pila.apilar(7);
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(8);

        System.out.println(pila);
        System.out.println("Talla antes de borrar: " + pila.talla());
        System.out.println("Elementos borrados: " + borrarMenor(pila, 4));
        System.out.println(pila);
        System.out.println("Talla después de borrar: " + pila.talla());
    }
}
