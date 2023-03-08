package divide;

public class Ej13PosicionIgualIndice {
    public static int ej3(int[] vector){
        return ej3(vector, 0,vector.length - 1);
    }
    private static int ej3(int[] vector, int inicio, int fin){
        int mitad = (inicio + fin) / 2;

        if(inicio > fin)
            return -1;
        // Caso base
        else if (vector[mitad] == mitad)
            return mitad;
        else if(vector[mitad] > mitad)
            return ej3(vector, 0, mitad - 1);
        else
            return ej3(vector, mitad + 1, fin);

    }
}
