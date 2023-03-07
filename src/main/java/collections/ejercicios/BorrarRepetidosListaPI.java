package collections.ejercicios;

import collections.implementacion.puntointeres.ListaConPIEnlazada;
import collections.modelos.ListaConPI;

public class BorrarRepetidosListaPI {
    public static <E> int borrarRepetidos(ListaConPI<E> lista){
        int elementosBorrados = 0;

        if(!lista.esVacia()){
            lista.inicio();
            E anterior = lista.recuperar();
            lista.siguiente();

            while (!lista.esFin()){
                E actual = lista.recuperar();
                if(anterior.equals(actual)){
                    lista.eliminar();
                    elementosBorrados++;
                } else {
                    anterior = actual;
                    lista.siguiente();
                }
            }

        }

        return elementosBorrados;
    }

    public static void main(String[] args) {
        ListaConPIEnlazada<Integer> lista = new ListaConPIEnlazada<>();
        lista.insertar(1);
        lista.insertar(1);
        lista.insertar(2);
        lista.insertar(2);
        lista.insertar(2);
        lista.insertar(3);
        lista.insertar(3);
        lista.insertar(4);
        lista.insertar(4);
        lista.insertar(5);
        lista.insertar(7);
        lista.insertar(7);
        lista.insertar(7);
        lista.insertar(8);
        lista.insertar(8);
        lista.insertar(8);

        System.out.println(lista);
        System.out.println("Talla antes de borrar: " + lista.talla());
        System.out.println("Elementos borrados: " + borrarRepetidos(lista));
        System.out.println(lista);
        System.out.println("Talla después de borrar: " + lista.talla());
    }
}
