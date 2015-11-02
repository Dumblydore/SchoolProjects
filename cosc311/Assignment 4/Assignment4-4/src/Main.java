/**
 * Created by medwar40 on 10/30/15.
 */
public class Main {

    public static void main(String... args) {
        RecursiveArrayList list = new RecursiveArrayList();
        list.display();
        System.out.println("sum of list: " + list.sum());
        System.out.println("====skip printing odd numbers====");
        list.printOdd();
        System.out.println("====skip printing lists====");
        list.skip();
        System.out.println("====printing reverse====");
        list.displayReverse();
    }
}
