import java.lang.reflect.Array;

/**
 * Created by medwar40 on 12/4/15.
 */
public class MergeSort {
    public static <V> void sort(V[] input) {
        mergeSort(input, 0, input.length - 1);
    }

    private static <V> void mergeSort(V input, int low, int high) {
        if(low < high) {
            int mid = (low + high)/2;
            mergeSort(input, low, mid);
            mergeSort(input, mid + 1, high);
//            merge(input, low, mid, high);
        }
    }

    private static <V> void merge(V[] input, int low, int mid, int high) {
//        V[] array = (V[]) Array.newInstance(vClass, high - low + 1);
        int p = low;
        int q = mid + 1;
        int r = 0;

        while (p <= mid && q <= high) {
            if (input[p].hashCode() < input[q].hashCode()) {

            }
        }

    }
}
