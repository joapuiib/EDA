package tema4.implementacion;

import tema1.implementacion.puntointeres.LEGListaConPI;
import tema1.modelos.ListaConPI;
import tema4.modelos.ColaPrioridad;

import java.util.Arrays;

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
        elArray[1] = elArray[talla];
        elArray[talla--] = null;
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

    public boolean estaEn(E e){
        return estaEn(e, 1);
    }
    private boolean estaEn(E e, int i){
        if(i > talla)
            return false;
        int cmp = e.compareTo(elArray[i]);
        if(cmp == 0)
            return true;
        else if(cmp < 0)
            return false;
        else
            return estaEn(e, i * 2) || estaEn(e, i * 2 + 1);
    }

    public void borrarHojasenRango(E x, E y){
        for (int i = talla / 2 + 1; i <= talla; i++) {
            E e = elArray[i];
            int cmpX = e.compareTo(x);
            int cmpY = e.compareTo(y);
            if(cmpX >= 0 && cmpY <= 0){
                if(i < talla) {
                    E ultim = elArray[talla];
                    int posIns = reflotar(ultim, i);
                    elArray[posIns] = ultim;
                }
                elArray[talla--] = null;
            }
        }
    }

    @Override
    public String toString() {
        String elArrayStr = Arrays.toString(Arrays.copyOfRange(elArray, 1, talla + 1));
        return elArrayStr;
    }

    public E eliminar(int k) {
        E eliminado = elArray[k];
        elArray[k] = elArray[talla];
        elArray[talla--] = null;
        hundir(k);
        return eliminado;
    }

    public int igualesAlMinimo(){
        if(talla == 0)
            return 0;
        else
            return iguales(recuperarMin(), 1);
    }
    public int iguales(E e, int i){
        if(i > talla)
            return 0;
        int cmp = e.compareTo(elArray[i]);
        if(cmp > 0){
            return 0;
        } else {
            int num = 0;
            if(cmp == 0)
                num++;

            int izq = iguales(e, i * 2);
            int der = iguales(e, i * 2 + 1);
            return num + izq + der;
        }
    }

    public static <E extends Comparable<E>> boolean esHeap(E[] v){
        int talla = v.length;
        for (int i = 1; i <= (talla / 2); i++) {
            E pare = v[i - 1];

            E izq = v[i * 2 - 1];
            if(pare.compareTo(izq) > 0)
                return false;

            if (i * 2 < talla) {
                E der = v[i * 2];
                if(pare.compareTo(der) > 0)
                    return false;
            }
        }
        return true;
    }
    public int menoresQue(E e){
        return menoresQue(e, 1);
    }
    private int menoresQue(E e, int i) {
        if (i > talla)
            return 0;
        int cmp = e.compareTo(elArray[i]);
        if (cmp > 0) {
            int izq = menoresQue(e, i * 2);
            int der = menoresQue(e, i * 2 + 1);
            return 1 + izq + der;
        } else {
            return 0;
        }
    }

    public E eliminar1Hoja(){
        int pos1Hoja = talla / 2 + 1;
        // return eliminar(pos1Hoja);
        E hoja = elArray[pos1Hoja];
        elArray[pos1Hoja] = elArray[talla];
        elArray[talla--] = null;
        return hoja;
    }

    public boolean valido(E e){
        return e.hashCode() % 2 == 0;
    }

    public void eliminarNoValidos(){
        int i = 1;
        // Recorremos el montículo entero
        while (i <= talla){
            // Eliminamos los elementos "no validos"
            if(!valido(elArray[i])){
                elArray[i] = elArray[talla];
                elArray[talla--] = null;
            } else {
                i++;
            }
        }

        for (int j = talla / 2; j >= 1 ; j--) {
            hundir(j);
        }
        /*
        for (int j = talla / 2 + 1; j <= talla ; j++) {
            E e = elArray[j];
            int posIns = reflotar(e, j);
            elArray[posIns] = e;
        }
        */
    }

    public boolean esValidoAppend(MonticuloBinario<E> mb){
        int j = 1;
        for (int i = talla / 2 + 1; i <= talla; i++) {
            for (int k = 0; k < 2; k++) {
                int cmp = elArray[i].compareTo(mb.elArray[j]);
                if(cmp > 0)
                    return false;
                j++;
            }
        }
        return true;
    }

    public ListaConPI<E> caminoDesdeMenorHoja(){
        ListaConPI<E> l = new LEGListaConPI<>();

        // E primeraHoja = elArray[talla / 2 + 1];
        // E ultimaHoja = elArray[talla];
        int indiceMin = talla / 2 + 1;
        for (int i = indiceMin + 1; i <= talla ; i++) {
            if(elArray[i].compareTo(elArray[indiceMin]) < 0){
                indiceMin = i;
            }
        }

        while (indiceMin >= 1){
            l.insertar(elArray[indiceMin]);
            indiceMin /= 2;
        }

        return l;
    }
}