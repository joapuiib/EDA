package tema2.ejercicios;

/**
 * Sea v un array de enteros positivos que se ajustan al perfil de una curva
 * cóncava, es decir, existe una única posición k de v, 0≤k<v.length, tal que:
 *
 * "j : 0≤j<k : v[j]>v[j+1] & "j : k<j<v.length : v[j-1]<v[j].
 *
 * Diseña el mejor método recursivo que calcule k y analiza su coste.
 */
public class Ej12CurvaConcava {

    public static int calcularK(int[] v){
        return calcularK(v, 0, v.length - 1);
    }

    public static int calcularK(int[] v, int inicio, int fin){
        int m = (inicio + fin) / 2;
        if(v[m] < v[m - 1]){
            if(v[m] < v[m + 1]){
                return m;
            }
            return calcularK(v, m + 1, fin);
        }
        return calcularK(v, inicio, m - 1);
    }
}
