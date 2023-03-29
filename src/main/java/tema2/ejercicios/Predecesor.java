package tema2.ejercicios;

public class Predecesor {
    public static <E extends Comparable<E>> E predecesor(E[] v, E e){
        return predecesor(v, e, 0, v.length - 1);
    }

    public static <E extends Comparable<E>> E predecesor(E[] v, E e, int i, int f){
        if(i > f)
            return null;
        else if (i == f){
            if(v[i].compareTo(e) < 0)
                return v[i];
            else
                return null;
        }

        int m = (i + f) / 2;
        if(v[m].compareTo(e) < 0 && v[m + 1].compareTo(e) >= 0){
            return v[m];
        } else if (v[m].compareTo(e) >= 0){
            return predecesor(v, e, i, m - 1);
        } else {
            return predecesor(v, e, m + 1, f);
        }
    }

    public static void main(String[] args) {
        Integer[] v = new Integer[]{1, 3, 5, 6, 8, 9};
        Integer a = predecesor(v, 10);
        System.out.println(a);
    }
}
