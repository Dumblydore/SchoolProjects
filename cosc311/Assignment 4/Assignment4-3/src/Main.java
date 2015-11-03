import java.util.Scanner;

/**
 * Created by medwar40 on 10/31/15.
 */
public class Main {


    public static void main(String[] args) {
        System.out.print("Enter number of disc pairs: ");
        Scanner scanner = new Scanner(System.in);
        int discs = scanner.nextInt();
        split(discs, "A", "B", "C");
    }

    public static void move(int n, String start, String end, String aux) {
        if (n == 1) {
            System.out.println("white disc " + n + " " + start + " -> " + end);
            System.out.println("black disc " + n + " " + start + " -> " + end);
        } else {
            move(n - 1, start, aux, end);
            System.out.println("white disc " + n + " " + start + " -> " + end);
            move(n - 1, aux, end, start);
        }

    }

    public static void split(int n, String start, String end, String aux) {
        if (n == 1) {
            System.out.println("(single) black disc " + n + " " + start + " -> " + end);
        } else {
            move(n - 1, start, aux, end);
            System.out.println("(single) white disc " + n + " " + start + " -> " + end);
            move(n - 1, aux, start, end);
            split(n - 1, start, end, aux);

        }
    }
}
