package divide;

public class Ej24SubsequenciaMax {
    public static int subSumaMax(int[] v){
        return subSumaMax(v, 0,v.length - 1);
    }
    private static int subSumaMax(int[] v, int inicio, int fin){
        // Si solo hay un elemento, se devuelve el valor si és positivo
        if(inicio == fin)
            return Math.max(v[inicio], 0);

        int mitad = (inicio + fin) / 2;
        // Se calcula el valor de la sequencia màxima en las dos mitades
        int subSumaMaxIzq = subSumaMax(v, inicio, mitad);
        int subSumaMaxDer = subSumaMax(v, mitad + 1, fin);

        // Miramos cual es la secuencia maxima que
        // pasa por la mitad

        // Calculamos el valor màximo de la sequencia de la parte IZQUIERDA
        // empezando por la mitad
        int sumaMaxBordeIzq = 0;
        int sumaBordeIzq = 0;
        for(int i = mitad; i >= inicio; i--){
            sumaBordeIzq += v[i];
            if(sumaBordeIzq > sumaMaxBordeIzq)
                sumaMaxBordeIzq = sumaBordeIzq;
        }

        // Calculamos el valor màximo de la sequencia de la parte DERECHA
        // empezando por la mitad
        int sumaMaxBordeDer = 0;
        int sumaBordeDer = 0;
        for(int i = mitad + 1; i <= fin; i++){
            sumaBordeDer += v[i];
            if(sumaBordeDer > sumaMaxBordeDer)
                sumaMaxBordeDer = sumaBordeDer;
        }

        int sumaMaxTotal = sumaMaxBordeIzq + sumaMaxBordeDer;

        // Se devuelve el màximo entre el maximo de la izq, el maximo de la derecha, o el maximo que pasa por la mitad.
        return Math.max(Math.max(subSumaMaxDer, subSumaMaxIzq), sumaMaxTotal);
    }
}
