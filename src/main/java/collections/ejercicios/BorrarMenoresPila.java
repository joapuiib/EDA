package collections.ejercicios;

import collections.ListaConPIEnlazada;
import collections.PilaPI;
import collections.modelos.ListaConPI;
import collections.modelos.Pila;

public class BorrarMenoresPila {
    public static <E extends Comparable<E>> int borrarMenor(Pila<E> pila, E dato){
        if(pila.esVacia())
            return 0;

        E objeto = pila.pop();

        int elementosBorrados = borrarMenor(pila, dato);

        if(objeto.compareTo(dato) >= 0){
            pila.push(objeto);
        } else {
            elementosBorrados++;
        }

        return elementosBorrados;
    }

    public static void main(String[] args) {
        Pila<Integer> pila = new PilaPI<>();
        pila.push(3);
        pila.push(4);
        pila.push(2);
        pila.push(5);
        pila.push(7);
        pila.push(1);
        pila.push(2);
        pila.push(8);

        System.out.println(pila);
        System.out.println("Talla antes de borrar: " + pila.talla());
        System.out.println("Elementos borrados: " + borrarMenor(pila, 4));
        System.out.println(pila);
        System.out.println("Talla después de borrar: " + pila.talla());
    }
}
