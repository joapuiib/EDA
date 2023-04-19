package tema4.implementacion;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema4.modelos.ColaPrioridad;

public class MonticuloBinario<E extends Comparable<E>> implements ColaPrioridad<E> {

    protected static final int CAPACIDAD_POR_DEFECTO = 100;
    protected E[] elArray;
    protected int talla;

    public MonticuloBinario(){
        this(CAPACIDAD_POR_DEFECTO);
    }

    @SuppressWarnings("unchecked")
    public MonticuloBinario(int n){
        elArray = (E[]) new Comparable[n];
        talla = 0;
    }

    @Override
    public void insertar(E e) {
        // PASO 1: Buscar la posición de inserción ordenada de e
        // (a) Preservar la Propiedad Estructural
        int posIns = ++talla;
        // (b) Preservar la Propiedad de Orden
        posIns = reflotar(e, posIns);
        // PASO 2: Insertar e en su posición de inserción ordenada
        elArray[posIns] = e;
    }

    protected int reflotar(E e, int posIns) {
        // posIns / 2 => És el pare de posIns
        while (posIns > 1 && e.compareTo(elArray[posIns / 2]) < 0) {
            elArray[posIns] = elArray[posIns / 2];
            posIns = posIns / 2;
        }
        return posIns;
    }


    @Override
    public E eliminarMin() {
        E elMinimo = elArray[1];
        // PASO 1: Borrar el mínimo del Heap
        // (a) Preservar la Propiedad Estructural:
        // borrar “Por Niveles” el mínimo
        elArray[1] = elArray[talla--];
        // (b) Preservar la Propiedad de Orden:
        // buscar posición de inserción ordenada de elArray[1]
        // PASO 2: Insertar elArray[1] en su posición ordenada
        hundir(1);
        return elMinimo;
    }

    protected void hundir(int pos) {
        int posActual = pos;
        E aHundir = elArray[posActual];
        int hijo = posActual * 2;
        boolean esHeap = false;
        while (hijo <= talla && !esHeap) {
            // Obté el menor dels dos fills
            if (hijo < talla && elArray[hijo + 1].compareTo(elArray[hijo]) < 0) {
                hijo++;
            }
            // Comprova si el fill és menor que el pare
            if (elArray[hijo].compareTo(aHundir) < 0) {
                elArray[posActual] = elArray[hijo];
                posActual = hijo;
                hijo = posActual * 2;
            }
            // Si és major, no cal afonar-lo més
            else {
                esHeap = true;
            }
        }
        elArray[posActual] = aHundir;
    }

    @Override
    public E recuperarMin() {
        return elArray[1];
    }

    @Override
    public boolean esVacia() {
        return talla == 0;
    }

    public boolean hayMenoresQue(E e){
        return recuperarMin().compareTo(e) < 0;
    }

    public boolean hayMayoresQue(E e){
        for (int i = (talla / 2) + 1; i <= talla; i++) {
            if(elArray[i].compareTo(e) > 0)
                return true;
        }
        return false;
    }
}
