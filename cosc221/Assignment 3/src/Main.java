import java.util.BitSet;

/**
 * Created by edw562 on 9/29/15.
 */
public class Main {
    public static void main(String... args) {
        System.out.println(addBinary("00111111", "00111111"));
    }


    public static String addBinary(String byte1, String byte2) {
        String sum = "";
        if(byte1.length() == 8 && byte2.length() == 8) {
            boolean isRounded = false;
            for(int i = 7; i > 0; i--) {
                char bit1 = byte1.charAt(i);
                char bit2 = byte2.charAt(i);
                System.out.println("bits: " + bit1 + ", " + bit2);
                if (bit1 == '1' && bit2 == '1') {
                    if(isRounded)
                        sum += '1';
                    else
                        sum += '0';
                    isRounded = true;
                } else if((bit1 == '1' || bit2 == '1') && isRounded) {
                    System.out.println("rounding!");
                        sum += '0';
                    isRounded = true;
                } else {
                    if(bit1 == '1' || bit2 == '1' || isRounded)
                        sum += '1';
                    else
                        sum += '0';
                    isRounded = false;
                }
            }
        }
        int decimal = byteToDec(sum, 0);
        if(decimal > 128)
            System.out.println("Too big");
        else
            System.out.print(decimal + "|");
        return sum;
    }

    //TODO update this algorithm
    public static int byteToDec(String byteString, int dec) {
        if(byteString.length() == 8 && byteString.charAt(0) == '1') {
            //lol
            byteString = byteString.replaceAll("0","x").replaceAll("1","0").replaceAll("x","1");
            System.out.print("-");
            dec = 1;
        }

        if (byteString.length() == 1) {
            int bit = Character.getNumericValue(byteString.charAt(0));
            return (int) (dec + bit * Math.pow(2, 0));
        }
        int bit = Character.getNumericValue(byteString.charAt(0));
        dec += bit * Math.pow(2, byteString.length()-1);
        //
        byteToDec(byteString.substring(1, byteString.length()), dec);
        return dec;
    }
}
