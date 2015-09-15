/*
*   Maurice Edwards
*   COSC 221
*   Fall Semester
*
*
*
*/
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
                    System.out.print("Enter number between 0-255> ");
                    int number = keyboard.nextInt();
                    //checking that input is within the bonuds of 0-255
                    if(number < 0 || number >  255) {
                        System.out.println(number + " is outside the bounds of a single byte, please enter numbers between 0-255");
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


/*
Options
1 -> Convert decimal into byte
2 -> Convert byte into decimal
0 -> quit
>1
Enter number between 0-255> 255
11111111
Options
1 -> Convert decimal into byte
2 -> Convert byte into decimal
0 -> quit
>2
Enter byte> 1011
11.0
Options
1 -> Convert decimal into byte
2 -> Convert byte into decimal
0 -> quit
>1
Enter number between 0-255> 300
300 is outside the bounds of a single byte, please enter numbers between 0-255
100101100
Options
1 -> Convert decimal into byte
2 -> Convert byte into decimal
0 -> quit
>1
Enter number between 0-255> -5
-5 is outside the bounds of a single byte, please enter numbers between 0-255
-5
Options
1 -> Convert decimal into byte
2 -> Convert byte into decimal
0 -> quit
>2
Enter byte> 11 11
3.0
Options
1 -> Convert decimal into byte
2 -> Convert byte into decimal
0 -> quit
>Invalid command, try again.

Options
1 -> Convert decimal into byte
2 -> Convert byte into decimal
0 -> quit
>0
Goodbye!
*/