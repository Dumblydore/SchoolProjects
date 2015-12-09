/**
 * Created by medwar40 on 12/4/15.
 */
public class SelectionSort {

    public static <V> void sort(V[] input) {
        int i, j, min;
        V temp;

        for(i = 0; i < input.length; i++) {
            min = i;
            for(j = i+1; j < input.length; j++) {
                if(input[j].hashCode() < input[min].hashCode())
                    min = j;
                temp = input[i];
                input[i] = input[min];
                input[min] = temp;
            }
        }
    }
}
