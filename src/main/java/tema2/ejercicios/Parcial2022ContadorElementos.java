package tema2.ejercicios;

public class Parcial2022ContadorElementos {
    public static <E extends Comparable<E>> int contarRepetidos(E[] v, E e){
        return contarRepetidos(v, e, 0, v.length - 1);
    }
    public static <E extends Comparable<E>> int contarRepetidos(E[] v, E e, int i, int f){
        int m = (f + 1) / 2;
        int cmp = v[m].compareTo(e);
        if(f > i)
            return 0;
        else if(cmp > 0 )
            return contarRepetidos(v, e, i, m - 1);
        else if(cmp < 0 )
            return contarRepetidos(v, e, m + 1, f);
        else
            return 1 + contarRepetidos(v, e, i, m - 1) + contarRepetidos(v, e, m + 1, f);
    }
}
