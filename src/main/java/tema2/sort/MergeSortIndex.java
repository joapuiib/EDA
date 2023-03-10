package tema2.sort;

import java.util.Arrays;

public class MergeSortIndex {

    public static void mergeSort(int[] v) {
        mergeSort(v, 0, v.length -1);
    }
    public static void mergeSort(int[] v, int i, int f){
        // print(v, i, f);
        // Hay uno o ningun elemento
        if(f - i <= 0)
            return;
        // Hay dos elementos
        else if (f - i == 1){
            if(v[i] > v[f])
                swap(v, i, f);
        } else {
            int m = (f + i) / 2;
            mergeSort(v, i, m);
            mergeSort(v, m + 1, f);
            merge(v, i, m, f);
        }
    }

    public static void swap(int[] v, int a, int b){
        int aux = v[a];
        v[a]= v[b];
        v[b] = aux;
    }

    public static void print(int v[], int i, int f){
        System.out.println(Arrays.toString(Arrays.copyOfRange(v, i, f + 1)));
    }

    private static void merge(int[] v, int inicio, int mitad, int fin){
        int[] aux = new int[fin - inicio + 1];
        int i = inicio,  j = mitad + 1, k = 0;
        while(i <= mitad && j <= fin){
            if(v[i] <= v[j])
                aux[k++] = v[i++];
            else
                aux[k++] = v[j++];
        }
        while(i <= mitad){
            aux[k++] = v[i++];
        }
        while(j <= fin){
            aux[k++] = v[j++];
        }
        for (k = 0; k < aux.length; k++)
            v[inicio + k] = aux[k];
    }
}
