package tema2.ejercicios;

public class Exponencial {

    public static int exponencial(int[] v, int e){
        return exponencial(v, e, 0, v.length - 1);
    }

    public static int exponencial(int[]v, int e, int i, int f){
        if(i <= f && i > 0 && f < v.length - 1) {
            int m = (i + f) / 2;
            int difIzq = v[m] - v[m - 1];
            int difDer = v[m + 1] - v[m];
            if (difIzq < e && difDer >= e)
                return v[m];
            else if (difDer < e)
                return exponencial(v, e, m + 1, f);
            else
                return exponencial(v, e, i, m - 1);
        } else
            return -1;
    }

    public static void main(String[] args) {
        int[] v = new int[]{1, 2, 4, 10, 20, 50, 100, 300, 800, 2000};
        int n = exponencial(v, 2250);
        System.out.println(n);
    }
}
