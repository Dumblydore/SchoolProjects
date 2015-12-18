import java.util.Random;
import java.util.Scanner;

/**
 * Created by medwar40 on 12/4/15.
 */
public class Main {

    private static final int MAX_RAND_NUM = (int) Math.pow(10, 9);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("options\n" +
                "a) insertion sort\n" +
                "b) selection sort\n" +
                "c) merge sort\n" +
                "d) quick sort> ");
        String option = in.next();
        System.out.print("enter seed> ");
        int seed = in.nextInt();
        System.out.print("enter array length> ");
        int length = in.nextInt();

        Integer[] arr  = generateArray(seed, length);
        long startTime = 0, runTime = 0;
        switch (option) {
            case "a":
                startTime = System.currentTimeMillis();
                InsertionSort.sort(arr);
                runTime = System.currentTimeMillis() - startTime;
            break;
            case "b":
                startTime = System.currentTimeMillis();
                SelectionSort.sort(arr);
                runTime = System.currentTimeMillis() - startTime;
            break;
            case "c":
                startTime = System.currentTimeMillis();
                MergeSort.sort(arr);
                runTime = System.currentTimeMillis() - startTime;
            break;
            case "d":
                startTime = System.currentTimeMillis();
                QuickSort.sort(arr);
                runTime = System.currentTimeMillis() - startTime;
            break;
        }
        System.out.println("Runtime: " + runTime);
    }

    private static Integer[] generateArray(int seed, int size) {
        Random random = new Random(seed);
        Integer[] arr = new Integer[size];
        for(int i = 0; i < size; i++)
            arr[i] = random.nextInt(MAX_RAND_NUM);
        return arr;
    }
}
