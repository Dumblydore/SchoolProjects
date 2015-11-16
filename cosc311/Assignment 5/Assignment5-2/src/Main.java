import java.math.BigDecimal;
import java.util.Random;


public class Main {
  public static final int MAX_NUM = 1000000000;
  public static final int[] SEEDS = {
    169,
    986,
    985,
    635,
    810,
    480,
    959,
    153,
    997,
    377
  };
  private static final String ROW = "Seed | Node Size | Tree Height | Search Time";
  private static float averageSearchTime = 0;
  private static int averageNodeSize = 0;
  private static int averageTreeHeight = 0;

  public static void main(String[] args) {
    int runs = 0;
    for (int n = 100; n < 1000000; n *= 10) {
      averageSearchTime = 0;
      averageNodeSize = 0;
      averageTreeHeight = 0;
      System.out.println("N Value " + n);
      System.out.println(ROW);
      System.out.println("=====================================");
      for (int seed : SEEDS) {
        BinarySearchTree<Integer> tree = initTree(seed, (int) Math.pow(10, 5));
        BigDecimal totalTime = searchTree(tree, seed);
        averageSearchTime += totalTime.floatValue();
        averageNodeSize += tree.nodeSize();
        averageTreeHeight += tree.height();
        System.out.println(seed + " | " + tree.nodeSize() + " | " + tree.height() + " | " + totalTime);
        System.out.println("------------------------------------");
        runs++;
      }
      System.out.println("Totals: " + averageNodeSize / runs + " | " +
        averageTreeHeight / runs + " | " + averageSearchTime / runs);
      System.out.println("=========================");
    }
  }

  private static BinarySearchTree<Integer> initTree(int seed, int length) {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    Random rand = new Random(seed);

    for (int i = 0; i < length; i++) {
      tree.add(rand.nextInt(MAX_NUM));
    }
    return tree;
  }

  private static BigDecimal searchTree(BinarySearchTree<Integer> tree, int seed) {
    int length = (int) Math.pow(10, 6);
    long startTime = System.currentTimeMillis();
    Random rand = new Random(seed);
    for (int i = 0; i < length; i++) {
      tree.find(rand.nextInt(MAX_NUM));
    }
    long endTime = System.currentTimeMillis();
    BigDecimal totalTime = BigDecimal.valueOf((endTime - startTime));
    return totalTime.divide(BigDecimal.valueOf(length));
  }
}
