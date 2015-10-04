/**
 * Created by Maurice on 9/24/2015.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("the sucker is: " + new SingleCircularLinkedList(5).circulate(2, 4));
        System.out.println("the sucker is: " + new SingleCircularLinkedList(50).circulate(31, 40));
        System.out.println("the sucker is: " + new SingleCircularLinkedList(70).circulate(60, 23));
        System.out.println("the sucker is: " + new SingleCircularLinkedList(100).circulate(39, 85));
        System.out.println();
    }
}
