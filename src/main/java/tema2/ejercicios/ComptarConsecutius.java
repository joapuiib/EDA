package tema2.ejercicios;

public class ComptarConsecutius {

    public static int comptarConsecutius(int[] v){
        return comptarConsecutius(v, 0, v.length - 1);
    }

    public static int comptarConsecutius(int[]v, int i, int f){
        if(i > f)
            return 0;
        int m = (i + f) / 2;
        if(v[m] == m + v[0]){
            int izq = comptarConsecutius(v, i, m - 1);
            int der = comptarConsecutius(v, m + 1, f);
            return 1 + izq + der;
        } else {
            return comptarConsecutius(v, i, m - 1);
        }
    }

    public static void main(String[] args) {
        int[] v = new int[]{-4, -3, -2, -1, 3, 5};
        int suma = comptarConsecutius(v);
        System.out.println(suma);
    }
}
