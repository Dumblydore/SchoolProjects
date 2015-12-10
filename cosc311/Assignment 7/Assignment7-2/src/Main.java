import java.util.Random;

/**
 * Created by Maurice on 12/10/2015.
 */
public class Main {
  public static void main(String[] args) {
    Integer[] seeds = new Integer[]{};
    int runs = 0;
    long startTime;
    long endTime;
    long selectionSortRunTime = 0;
    long insertionSortRunTime = 0;
    long mergeSortRunTime = 0;
    long quickSortRunTime = 0;
      Integer[] testArr = generateArray(37, 10);

    for(Integer i : testArr)
      System.out.println(i);
    System.out.println("=");
      startTime = System.currentTimeMillis();
      TreeSort.sort(testArr);
      for(Integer i : testArr)
        System.out.println(i);

  }
  private static Integer[] generateArray(int seed, int size) {
    Random random = new Random(seed);
    Integer[] arr = new Integer[size];
    for(int i = 0; i < size; i++)
      arr[i] = random.nextInt(100000);
    return arr;
  }
}
