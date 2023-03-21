package tema2.ejercicios;

/**
 * Dado un array de n enteros en orden ascendente, con
 * valores en el rango [1 ... n], queremos encontrar, si existe, el único valor entero que se
 * repite. Si no se encuentra ningún elemento repetido, se indicará con el valor especial -1.
 */
public class Ej25EncontrarRepetido {

    public static int encontrarRepetido(int[] v){
        return encontrarRepetido(v, 0, v.length - 1);
    }

    public static int encontrarRepetido(int[] v, int i, int f){
        if(i > f)
            return -1;

        int m = (i + f) / 2;
        if(v[m] == m){
            if(m > 0 && v[m - 1] == v[m])
                return v[m];
            else
                return encontrarRepetido(v, i, m - 1);
        } else {
            return encontrarRepetido(v, m + 1, f);
        }
    }
}
