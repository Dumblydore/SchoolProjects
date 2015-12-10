import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.util.Random;

/**
 * Created by medwar40 on 12/4/15.
 */
public class Main {

    private static final int MAX_RAND_NUM = (int) Math.pow(10, 9);

    public static void main(String[] args) {
        Integer[] seeds = new Integer[]{};
        int runs = 0;
        long startTime;
        long endTime;
        long selectionSortRunTime = 0;
        long insertionSortRunTime = 0;
        long mergeSortRunTime = 0;
        long quickSortRunTime = 0;
        for(int seed: seeds) {
            for (int i = 10; i < 1000000; i *= 10) {
                System.out.println("===== " + i + " =====");
                Integer[] testArr = generateArray(seed, i);
                System.out.println("===== selectionSort =====");
                startTime = System.currentTimeMillis();
                SelectionSort.sort(testArr);
                endTime = System.currentTimeMillis();
                System.out.println("Runtime: " + (endTime - startTime));
                selectionSortRunTime += (endTime - startTime);
                System.out.println("===========");
                testArr = generateArray(seed, i);
                System.out.println("===== insertionSort =====");
                startTime = System.currentTimeMillis();
                InsertionSort.sort(testArr);
                endTime = System.currentTimeMillis();
                System.out.println("Runtime: " + (endTime - startTime));
                insertionSortRunTime += (endTime - startTime);
                System.out.println("===========");
                testArr = generateArray(seed, i);
                System.out.println("===== mergeSort =====");
                startTime = System.currentTimeMillis();
                MergeSort.sort(testArr);
                endTime = System.currentTimeMillis();
                System.out.println("Runtime: " + (endTime - startTime));
                mergeSortRunTime +=  + (endTime - startTime);
                System.out.println("===========");
                testArr = generateArray(seed, i);
                System.out.println("===== quickSort =====");
                startTime = System.currentTimeMillis();
                QuickSort.sort(testArr);
                endTime = System.currentTimeMillis();
                System.out.println("Runtime: " + (endTime - startTime));
                quickSortRunTime +=  + (endTime - startTime);
                System.out.println("===========");
                runs++;
            }
        }

        System.out.println("selectionSortRunTime: " + selectionSortRunTime );
        System.out.println("insertionSortRunTime: " + insertionSortRunTime );
        System.out.println("mergeSortRunTime: " + mergeSortRunTime );
        System.out.println("quickSortRunTime: " + quickSortRunTime );
    }

    private static Integer[] generateArray(int seed, int size) {
        Random random = new Random(seed);
        Integer[] arr = new Integer[size];
        for(int i = 0; i < size; i++)
            arr[i] = random.nextInt(MAX_RAND_NUM);
        return arr;
    }
}
