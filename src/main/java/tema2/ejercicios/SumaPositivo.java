package tema2.ejercicios;

public class SumaPositivo {

    public static int sumaPositivo(int[] v){
        return sumaPositivo(v, 0, v.length - 1);
    }

    public static int sumaPositivo(int[]v, int i, int f){
        if(i > f)
            return 0;
        int m = (i + f) / 2;
        if(v[m] < 0)
            return sumaPositivo(v, m + 1, f);
        else {
            int izq = sumaPositivo(v, i, m - 1);
            int der = sumaPositivo(v, m + 1, f);
            return v[m] + izq + der;
        }
    }

    public static void main(String[] args) {
        int[] v = new int[]{-4, -3, -1, 1, 3, 5};
        int suma = sumaPositivo(v);
        System.out.println(suma);
    }
}
