package tema2.ejercicios;

/**
 * Sea v un array de enteros que se ajustan al perfil de una curva continua
 * monotónicamente creciente, tal que v[0]<0 y v[v.length-1]>0.
 *
 * Existe una única posición k de v, 0≤k<v.length-1, tal que entre v[k] y v[k+1] la función es
 * 0, es decir, tal que v[k]≤0 y v[k+1]>0.
 *
 * Diseña el "mejor" método recursivo que calcule k y analiza su coste.
 *
 * Sea V = [-3, -2, -1, 1, 4, 5], k = 2
 */
public class Ej11CurvaContinua {

    public static int calcularK(int[] v){
        return calcularK(v, 0, v.length - 1);
    }

    public static int calcularK(int[] v, int inicio, int fin){
        int medio = (inicio + fin) / 2;
        if(v[medio] <= 0 && v[medio + 1] > 0)
            return medio;
        else if(v[medio] < 0)
            return calcularK(v, medio+1, fin );
        else
            return calcularK(v, inicio, medio);
    }
}
