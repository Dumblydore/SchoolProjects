/*
*   Maurice Edwards
*   COSC 221
*   Fall Semester
*
*  This program converts an 8-bit binary number into a decimal and a decimal between -128-127 to a 8-bit binary number.
*
*/
import java.util.Scanner;
public class Assignment2 {

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
                    System.out.print("Enter an unsigned number between (-128-127)> ");
                    int number = keyboard.nextInt();
                    //checking that input is within the bonuds of -128-127
                    if(number < -128 || number >  127) {
                        System.out.println(number + " is outside the bounds of a single byte, please enter numbers between -128-127");
                    }
                    decToByte(number);
                    break;
                case 2:
                    System.out.print("Enter byte> ");
                    String byteString = keyboard.next();
                    if(byteString.contains(" ") || byteString.length() > 8) {
                        System.out.println(byteString + " is not a valid 8 bit binary number, try again.");
                        return;
                    }
                    byteToDec(byteString, 0);
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
        if(number < 0) {
            number *= -1;
        }
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
        if(byteString.length() == 8 && byteString.charAt(0) == '1') {
            //lol
            byteString = byteString.replaceAll("0","x").replaceAll("1","0").replaceAll("x","1");
            System.out.print("-");
            dec = 1;
        }

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
