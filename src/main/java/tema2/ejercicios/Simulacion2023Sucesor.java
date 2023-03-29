package tema2.ejercicios;

public class Simulacion2023Sucesor {
    public static Integer sucesor(int[] v, int e){
        return sucesor(v, e, 0, v.length - 1);
    }

    public static Integer sucesor(int[] v, int e, int i, int f){
        if(i > f)
            return null;
        else if(i == f){
            if(v[i] > e)
                return v[i];
        }

        int m = (i + f) / 2;
        if(v[m] > e){
            if(v[m-1] <= e){
                return v[m];
            }
            return sucesor(v, e, i, m - 1);
        } else {
            return sucesor(v, e, m + 1, f);
        }
    }
}
