import java.util.Scanner;

/**
 * Created by medwar40 on 12/10/15.
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter size of array> ");
        Integer[] arr = new Integer[input.nextInt()];
        for(int i = 0; i < arr.length; i++) {
            System.out.print("Enter number");
            arr[i] = input.nextInt();
        }
        long startTime = System.currentTimeMillis();
        TreeSort.sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("array sorted. Runtime -> " + (endTime - startTime));
    }
}
