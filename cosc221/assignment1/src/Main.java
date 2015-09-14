import java.util.Scanner;

public class Main {

    private static String menu = "Options\n" +
            "1 -> Convert decimal into byte\n" +
            "2 -> Convert byte into decimal\n" +
            "0 -> quit\n" +
            ">";

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.print(menu);
            switch (keyboard.nextInt()) {
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                case 1:
                    byteToDec(keyboard.next(), 0);
                    break;
                case 2:
                    decToByte(keyboard.nextInt());
                    break;
                default:
                    System.out.println("Invalid command, try again.");
                    break;
            }
            System.out.println();
        }
    }

    //Both methods are recursive
    public static void decToByte(int number) {
        if (number <= 1) {
            System.out.print(number);
            return;
        }
        int bit = number % 2;
        //
        decToByte(number >>> 1);
        System.out.print(bit);
    }

    public static void byteToDec(String byteString, int dec) {
        if (byteString.length() == 1) {
            int bit = Character.getNumericValue(byteString.charAt(0));
            System.out.print(dec + bit * Math.pow(2, 0));
            return;
        }
            int bit = Character.getNumericValue(byteString.charAt(0));
            dec += bit * Math.pow(2, byteString.length()-1);
            //
            byteToDec(byteString.substring(1, byteString.length()), dec);
    }
}


