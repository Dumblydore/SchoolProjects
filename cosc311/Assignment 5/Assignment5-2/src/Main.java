import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;


public class Main {
  public static final int MAX_NUM = 1000000000;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Enter N value");
    int n = in.nextInt();
    System.out.print("Enter seed");
    int seed = in.nextInt();
    BinarySearchTree<Integer> tree = initTree(seed, n);
    BigDecimal totalTime = searchTree(tree, seed);
    System.out.println("Average search time: " + totalTime);
  }


  //generates tree
  private static BinarySearchTree<Integer> initTree(int seed, int length) {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    Random rand = new Random(seed);
    for (int i = 0; i < length; i++) {
      tree.add(rand.nextInt(MAX_NUM));
    }
    return tree;
  }

  //search tree and return time it took to search
  private static BigDecimal searchTree(BinarySearchTree<Integer> tree, int seed) {
    int length = (int) Math.pow(10, 6);
    long startTime = System.currentTimeMillis();
    Random rand = new Random(seed);
    for (int i = 0; i < length; i++) {
      tree.find(rand.nextInt(MAX_NUM));
    }
    /*
    * on my machine the result ended up being so small that i had to use bigDecimal
    * to properly display the number.
    * */
    long endTime = System.currentTimeMillis();
    BigDecimal totalTime = BigDecimal.valueOf((endTime - startTime));
    return totalTime.divide(BigDecimal.valueOf(length));
  }
}
