import java.util.Scanner;

/**
 * Created by edw562 on 9/29/15.
 */
public class Main {
    private static final String MENU = "****Menu****\n" +
            "1 -> Add\n" +
            "2 -> Subtract\n" +
            "0 -> quit\n" +
            ">";
    public static void main(String... args) {
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            String byteString;
            String byteString2;
            System.out.print(MENU);
            switch (keyboard.nextInt()) {
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                case 1:
                    System.out.print("Enter 8 bit signed binary number (10000000 - 01111111)> ");
                    byteString = cleanInput(keyboard.next());
                    System.out.print("Enter 2nd Number> ");
                    byteString2 = cleanInput(keyboard.next());
                    if(byteString.contains(" ") || byteString.length() > 8) {
                        System.out.println(byteString + " is not a valid 8 bit binary number, try again.");
                        return;
                    }
                    String sum = add(byteString, byteString2);
                    System.out.print(sum + "(");
                    byteToDec(sum, 0);
                    System.out.println(")");
                    break;
                case 2:
                    System.out.print("Enter 8 bit signed binary number (10000000 - 01111111)> ");
                    byteString = cleanInput(keyboard.next());
                    System.out.print("Enter 2nd Number> ");
                    byteString2 = cleanInput(keyboard.next());
                    if(byteString.contains(" ") || byteString.length() > 8) {
                        System.out.println(byteString + " is not a valid 8 bit binary number, try again.");
                        return;
                    }
                    String difference = subtract(byteString, byteString2);
                    System.out.print(difference + "(");
                    byteToDec(difference, 0);
                    System.out.println(")");
                    break;
                default:
                    System.out.println("Invalid command, try again.");
                    break;
            }
            System.out.println();
        }
    }

    private static String cleanInput(String in) {
        StringBuilder stringBuilder = new StringBuilder(in);
        for(int i = in.length(); i < 8; i++) {
            stringBuilder.insert(1, in.charAt(0));
        }
        return stringBuilder.toString();
    }

    public static String add(String byte1, String byte2) {
        if (byte1.charAt(0) == '1')
            subtract(byte2, byte1);
        if (byte2.charAt(0) == '1')
            subtract(byte1, byte2);
        StringBuilder sum = new StringBuilder();
        if (byte1.length() == 8 && byte2.length() == 8) {
            boolean isRounded = false;
            for (int i = 7; i > -1; i--) {
                char bit1 = byte1.charAt(i);
                char bit2 = byte2.charAt(i);
                if (bit1 == '1' && bit2 == '1') {
                    if (isRounded)
                        sum.insert(0, '1');
                    else
                        sum.insert(0, '0');
                    isRounded = true;
                } else if ((bit1 == '1' || bit2 == '1') && isRounded) {
                    sum.insert(0, '0');
                    isRounded = true;
                } else {
                    if (bit1 == '1' || bit2 == '1' || isRounded)
                        sum.insert(0, '1');
                    else
                        sum.insert(0, '0');
                    isRounded = false;
                }
            }
        }
        return sum.toString();
    }

    public static String subtract(String byte1, String byte2) {
        byte2 = add(byte2.replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1"), "00000001");
        return add(byte1, byte2);
    }

    //TODO update this algorithm
    public static void byteToDec(String byteString, int dec) {
        if(byteString.length() == 8 && byteString.charAt(0) == '1') {
            System.out.print("-");
            byteString = byteString.replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1");
            dec = 1;
        }

        if(byteString.length() == 1) {
            int bit = Character.getNumericValue(byteString.charAt(0));
            System.out.print(dec + bit * Math.pow(2, 0));
            return;
        }

        int bit = Character.getNumericValue(byteString.charAt(0));
        dec += bit * Math.pow(2, byteString.length() - 1);
        byteToDec(byteString.substring(1), dec);
    }
}

/*
****Menu****
1 -> Add
2 -> Subtract
0 -> quit
>1
Enter 8 bit signed binary number (10000000 - 01111111)> 010100
Enter 2nd Number> 010100
00101000(40.0)

****Menu****
1 -> Add
2 -> Subtract
0 -> quit
>2
Enter 8 bit signed binary number (10000000 - 01111111)> 010100
Enter 2nd Number> 010100
00000000(0.0)
Invalid command, try again.

****Menu****
1 -> Add
2 -> Subtract
0 -> quit
>0
Goodbye!
*/
