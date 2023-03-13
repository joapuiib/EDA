package tema2.sort;

import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] v){
        quickSort(v, 0, v.length - 1, 0);
    }

    public static void quickSort(int[] v, int i, int f, int depth){
        if(i < f){
            print(v, i, f, depth, "Before");
            int p = particion(v, i, f);
            System.out.println("P: " + p);
            print(v, i, f, depth, "After");
            quickSort(v, i, p, depth + 1);
            quickSort(v, p + 1, f, depth + 1);
        }
    }

    public static int particion(int[] v, int i, int f){
        int p = v[f];
        int k = i;
        for (int j = i; j <= f; j++){
            if(v[j] < p){
                swap(v, k++, j);
            }
        }
        swap(v, k, f);
        return k;
    }

    public static void swap(int[] v, int a, int b){
        int aux = v[a];
        v[a] = v[b];
        v[b] = aux;
    }

    public static void print(int v[], int i, int f, int depth, String msg){
        String indent = "     ".repeat(depth);
        String array = Arrays.toString(Arrays.copyOfRange(v, i, f + 1));
        System.out.printf("%s %d: %s%s\n", msg, depth, indent, array);
    }
}
