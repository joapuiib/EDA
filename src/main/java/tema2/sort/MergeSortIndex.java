package tema2.sort;

import java.util.Arrays;

public class MergeSortIndex {

    public static void mergeSort(int[] v) {
        mergeSort(v, 0, v.length -1, 0);
    }
    public static void mergeSort(int[] v, int i, int f, int depth){
        print(v, i, f, depth, "before");
        // Hay uno o ningun elemento
        if(f - i <= 0)
            return;
        // Hay dos elementos
        else if (f - i == 1){
            if(v[i] > v[f])
                swap(v, i, f);
        } else {
            int m = (f + i) / 2;
            mergeSort(v, i, m, depth + 1);
            mergeSort(v, m + 1, f, depth + 1);
            merge(v, i, m, f);
        }

        print(v, i, f, depth, "after");
    }

    public static void swap(int[] v, int a, int b){
        int aux = v[a];
        v[a]= v[b];
        v[b] = aux;
    }

    public static void print(int v[], int i, int f, int depth, String msg){
        String indent = "     ".repeat(depth);
        String array = Arrays.toString(Arrays.copyOfRange(v, i, f + 1));
        System.out.printf("%s %d: %s%s\n", msg, depth, indent, array);
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
