import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Maurice on 10/3/2015.
 */
public class Main {
    private static Stack<String> values;
    private static Stack<Integer> variables;

    public static void main(String... args) {
        values = new Stack<>();
        variables = new Stack<>();
        System.out.println(validate("+ 13 24"));
        System.out.println(validate("* 4 - 165 235"));
        System.out.println(validate("% * 5 12 8"));
        System.out.println(validate("+ / 217 2 * 4 + 10 5"));
        System.out.println(validate("+ * - 18 + 15 5 + 6 25 / * 4 + - 50 31 6 18"));
    }

    private static int validate(String equation) {
        StringTokenizer tokenizer = new StringTokenizer(equation);
        while(tokenizer.hasMoreTokens()) {
            values.push(tokenizer.nextToken());
        }
        while(values.size() != 0) {
            String value = values.pop();
            if(isNumeric(value)) {
                variables.push(Integer.parseInt(value));
            } else {
                variables.push(calculate(variables.pop(), variables.pop(), value.charAt(0)));
            }
        }
        return variables.pop();
    }

    private static int calculate(int number1, int number2, char type) {
        switch (type) {
            case '+':
                return number1 + number2;
            case '-':
                return number1 - number2;
            case '*':
                return number1 * number2;
            case '/':
                return number1 / number2;
            case '%':
                return number1 % number2;
        }
        return -1;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
