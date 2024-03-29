package tema1.implementacion.puntointeres;

import tema1.modelos.Cola;

public class LEGCola<E> extends LEGListaConPI<E> implements Cola<E> {
    @Override
    public void encolar(E item) {
        fin();
        insertar(item);
    }

    @Override
    public E desencolar() {
        inicio();
        E objeto = recuperar();
        eliminar();
        return objeto;
    }

    @Override
    public E primero() {
        inicio();
        return recuperar();
    }

    public static void main(String[] args) {
        LEGCola<String> cola = new LEGCola<>();
        cola.encolar("Item1");
        cola.encolar("Item2");
        cola.encolar("Item3");
        cola.encolar("Item4");

        int i = 0;
        while (!cola.esVacia()) {
            System.out.println("=======" + i++ + "=======");
            System.out.println(cola);
            String item = cola.desencolar();
            System.out.println(item);
        }
    }
}
