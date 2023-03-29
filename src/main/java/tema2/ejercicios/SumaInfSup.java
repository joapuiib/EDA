package tema2.ejercicios;

public class SumaInfSup {

    public static int sumaInfSup(int[] v, int inf, int sup){
        return sumaInfSup(v, inf, sup, 0, v.length - 1);
    }

    public static int sumaInfSup(int[]v, int inf, int sup, int i, int f){
        if(i > f)
            return 0;
        int m = (i + f) / 2;
        if(v[m] < inf)
            return sumaInfSup(v, inf, sup, m + 1, f);
        else if(v[m] > sup)
            return sumaInfSup(v, inf, sup, i, m - 1);
        else {
            int izq = sumaInfSup(v, inf, sup, i, m - 1);
            int der = sumaInfSup(v, inf, sup, m + 1, f);
            return v[m] + izq + der;
        }
    }

    public static void main(String[] args) {
        int[] v = new int[]{1, 2, 3, 4, 5, 5, 5, 5, 5, 6, 7, 8};
        int suma = sumaInfSup(v, 1, 3);
        System.out.println(suma);
    }
}
