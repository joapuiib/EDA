package tema2.sort;

public class MergeSort {

    public static int[] mergeSort(int[] v){
        if(v.length <= 1)
            return v;
        else if (v.length == 2){
            if(v[0] > v[1]){
                swap(v, 0, 1);
            }
        }

        int m = v.length / 2;
        int[] a = mergeSort(slice(v, 0, m - 1));
        int[] b = mergeSort(slice(v, m, v.length - 1));
        return merge(a, b);
    }

    private static void swap(int v[], int a, int b){
        int aux = v[a];
        v[a] = v[b];
        v[b] = aux;
    }
    private static int[] slice(int[] v, int i, int f){
        int[] a = new int[f - i + 1];
        for (int j = 0; j <= (f - i); j++) {
            a[j] = v[i + j];
        }
        return a;
    }

    private static int[] merge(int[] a, int[] b){
        int[] v = new int[a.length + b.length];
        int i = 0,  j = 0, k = 0;
        while(i < a.length && j < b.length){
            if(a[i] <= b[j])
                v[k++] = a[i++];
            else
                v[k++] = b[j++];
        }
        while(i < a.length)
            v[k++] = a[i++];
        while(j < b.length)
            v[k++] = b[j++];
        return v;
    }
}
