import java.util.Scanner;

/**
 * Created by medwar40 on 10/30/15.
 */
public class Main {

    private static String MENU = "-RecursiveArrayList Menu-" +
            "a - generate dataset\n" +
            "b - display\n" +
            "c - add all\n" +
            "d - display number of zeros\n" +
            "e - display odd numbers\n" +
            "f - skip\n" +
            "g - display in reverse\n" +
            "q - quit\n" +
            ">";

    public static void main(String... args) {
        RecursiveArrayList list = new RecursiveArrayList();
        Scanner keyboard = new Scanner(System.in);
        System.out.print("(A)rray or (S)ingly linked list? > ");
        String choice = keyboard.next();
        if(choice.equalsIgnoreCase("A"))
            aMenu(keyboard);
        else if(choice.equalsIgnoreCase("S"))
            sMenu(keyboard);
        else
            System.out.println("Invalid option!");
    }

    public static void sMenu(Scanner keyboard) {
        RecursiveSinglyLinkedList list = new RecursiveSinglyLinkedList();
        while (true) {
            System.out.print(MENU);
            String option = keyboard.next();
            switch (option) {
                case "a":
                    list = new RecursiveSinglyLinkedList();
                    break;
                case "b":
                    list.display();
                    break;
                case "c":
                    list.sum();
                    break;
                case "d":
                    list.containsZero();
                    break;
                case "e":
                    list.printOdd();
                    break;
                case "f":
                    list.skip();
                    break;
                case "g":
                    list.displayReverse();
                    break;
                case "q":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public static void aMenu(Scanner keyboard) {
        RecursiveArrayList list = new RecursiveArrayList();
        while (true) {
            System.out.print(MENU);
            String option = keyboard.next();
            switch (option) {
                case "a":
                    list = new RecursiveArrayList();
                    break;
                case "b":
                    list.display();
                    break;
                case "c":
                    list.sum();
                    break;
                case "d":
                    list.containsZero();
                    break;
                case "e":
                    list.printOdd();
                    break;
                case "f":
                    list.skip();
                    break;
                case "g":
                    list.displayReverse();
                    break;
                case "q":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
