/**
 * Created by medwar40 on 12/4/15.
 */
public class QuickSort {
    public static <V> void sort(V[] input) {
        quickSort(input, 0, input.length - 1);
    }

    private static <V> void quickSort(V[] arr, int low, int high) {
        if (low < high) {
            int mid = partition(arr, low,  high);
            quickSort(arr, low, mid - 1);
            quickSort(arr, mid + 1, high);
        }
    }

    private static <V> int partition(V[] input, int low, int high) {
        int temp = low;

        for (int i = low + 1; i <= high; i++) {
            if(input[i].hashCode() < input[low].hashCode()) {
                temp++;
                swap(input, i, temp);
            }
        }
        swap(input, low, temp);
        return temp;
    }

    private static <V> void swap(V[] in, int i, int j) {
        V temp = in[i];
        in[i] = in[j];
        in[j] = temp;
    }
}
