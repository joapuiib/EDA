package divide;

/**
 * Sea v, un array de enteros positivos, ordenado descendentemente y que puede contener valores repetidos,
 * que representa las citas a las publicaciones de un investigador (número de veces que cada publicación ha
 * sido referenciada desde otras publicaciones).
 *
 * Se quiere obtener h, el índice del investigador cuyas citas están representadas en v. El índice h vale x, si x
 * publicaciones han sido citadas más de x veces.
 *
 * Ejemplos:
 * - Si v es [2,2,1,1,1,1,1,1,1,1,1], h es 2.
 * - Si v es [3,3,3,2,1], h es 3.
 * - Si v es [10,9,8,7,6,3,2,1], h es 5.
 * - Si v es [61,41,40,35,32,32,30,27,23,22,18,17,17,16,15,14,14,12,12], h es 15
 *
 * Según la definición de índice h en Wikipedia: "Un investigador tiene un índice h si h de sus N trabajos
 * tienen al menos h citas cada uno, y los otros N‐h trabajos no tienen más de h citas cada uno".
 */
public class Rec2021IndiceInvestigacionH {

    public static int calcularH(int[] v){
        return calcularH(v, 0, v.length - 1, 0);
    }

    public static int calcularH(int[] v, int inicio, int fin, int h){
        if(inicio > fin) return h;
        int medio = (inicio + fin) / 2;
        if (v[medio] >= medio + 1)
            return calcularH(v, medio + 1, fin, medio + 1);
        else
            return calcularH(v, inicio, medio - 1, h);
    }
}
