/**
 * Created by medwar40 on 12/4/15.
 */
public class InsertionSort {
    public static <V> void sort(V[] input) {
        int i, j;
        V temp;

        for (i = 0; i < input.length; i++) {
            temp = input[i];
            for(j = i-1; j >= 0 && temp.hashCode() < input[j].hashCode(); j++)
                input[j + 1] = input[j];
            input[j+1] = temp;
        }
    }
}
