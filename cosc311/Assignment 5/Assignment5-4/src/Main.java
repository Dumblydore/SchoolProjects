import java.util.Scanner;

public class Main {
  public static final String MENU =
    "a) create new tree\n" +
    "b) count number of nodes in tree\n" +
    "c) display leaves in tree\n" +
    "d) get height of tree\n" +
    "e) get sum of odd number in tree\n" +
    "f) checks for 0 in tree\n" +
    "g) display tree in descending order\n" +
    "h) display even number of tree in ascending order\n" +
    "i) display minimum value in tree\n" +
    "j) finds value in tree & prints path\n" +
      "q) quit\n" +
    "> ";
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    BinarySearchTree tree = initTree(input);
    System.out.println();

    while (true) {
      System.out.print(MENU);
      switch (input.next()) {
        case "a":
          tree = initTree(input);
          break;
        case "b":
          System.out.println("number of nodes: " + tree.nodeSize());
          break;
        case "c":
          System.out.print("==========displaying tree leaves==========");
          tree.displayLeaves();
          System.out.print("==========================================");
          break;
        case "d":
          System.out.println("height: " + tree.height());
          break;
        case "e":
          System.out.println("sum of odd numbers: " + tree.sumOdd());
          break;
        case "f":
          System.out.println("Tree " + (tree.findZero() ? "has" : "does not have") + "a zero");
          break;
        case "g":
          System.out.print("====displaying tree in descending order====");
          tree.displayDescending();
          System.out.print("===========================================");
          break;
        case "h":
          System.out.print("======displaying even numbers in tree======");
          tree.displayAscendingEven();
          System.out.print("===========================================");
          break;
        case "i":
          System.out.println("minimum value: " + tree.getMinimumValue());
          break;
        case "j":
          tree.find(19);
          break;
        case "q":
          System.exit(0);
          break;
        default:
          System.out.println("Invalid command!");
          break;
      }
    }
  }

  //gets numbers from user
  private static BinarySearchTree initTree(Scanner in) {
    int[] numbers = new int[20];
    for(int i = 0; i < numbers.length; i++) {
      System.out.print("Enter number (" + (i+1) + " of 20)> ");
      numbers[i] = in.nextInt();
    }
    return new BinarySearchTree(numbers);
  }
}
