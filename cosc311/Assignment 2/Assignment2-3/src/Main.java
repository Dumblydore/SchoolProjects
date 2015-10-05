import java.util.Stack;

/**
 * Created by edw562 on 10/4/15.
 */
public class Main {

    private static Stack<LineCharacter> parenthesisStack;
    private static Stack<LineCharacter> squareStack;
    private static Stack<LineCharacter> braceStack;

    public static void main(String... args) {
        parenthesisStack = new Stack<LineCharacter>();
        squareStack = new Stack<LineCharacter>();
        braceStack = new Stack<LineCharacter>();
        try {
            textCheck("{{}{}\n\n}{}]");
        } catch (ParenthesisImbalanceErrorException e) {
            e.printStackTrace();
        }
    }

    private static void textCheck(String input) throws ParenthesisImbalanceErrorException {
        int lineNumber = 1;
        for(char c : input.toCharArray()) {
            switch (c) {
                case '{':
                    braceStack.push(new LineCharacter('{', lineNumber));
                    break;
                case '}':
                    if(braceStack.isEmpty()) {
                    if(!parenthesisStack.isEmpty())
                        throw new ParenthesisImbalanceErrorException(lineNumber, "( closed with }");
                    else if (!squareStack.isEmpty())
                        throw new ParenthesisImbalanceErrorException(lineNumber, "[ closed with }");
                    else
                        throw new ParenthesisImbalanceErrorException(lineNumber, "Premature closing of {");
                }
                    braceStack.pop();
                    break;
                case '(':
                    parenthesisStack.push(new LineCharacter('(', lineNumber));
                    break;
                case ')':
                    if(parenthesisStack.isEmpty()) {
                        if(!braceStack.isEmpty())
                            throw new ParenthesisImbalanceErrorException(lineNumber, "{ closed with )");
                        else if (!squareStack.isEmpty())
                            throw new ParenthesisImbalanceErrorException(lineNumber, "[ closed with )");
                        else
                            throw new ParenthesisImbalanceErrorException(lineNumber, "Premature closing of (");
                    }
                    parenthesisStack.pop();
                    break;
                case '[':
                    squareStack.push(new LineCharacter('[', lineNumber));
                    break;
                case ']':
                    if(squareStack.isEmpty()) {
                    if(!parenthesisStack.isEmpty())
                        throw new ParenthesisImbalanceErrorException(lineNumber, "( closed with ]");
                    else if (!braceStack.isEmpty())
                        throw new ParenthesisImbalanceErrorException(lineNumber, "{ closed with ]");
                    else
                        throw new ParenthesisImbalanceErrorException(lineNumber, "Premature closing of [");
                }
                    squareStack.pop();
                    break;
                case '\n':
                    lineNumber++;
            }
        }

        if(!braceStack.isEmpty())
            throw new ParenthesisImbalanceErrorException(braceStack.pop(), " { not closing");
        if(!parenthesisStack.isEmpty())
            throw new ParenthesisImbalanceErrorException(parenthesisStack.pop(), " ( not closing");
        if(!squareStack.isEmpty())
            throw new ParenthesisImbalanceErrorException(squareStack.pop(), " [ not closing");
    }

    public static class ParenthesisImbalanceErrorException extends Exception {
        public ParenthesisImbalanceErrorException(int lineNumber, String message) {
            super(message + " | Occurred on line number: " + lineNumber);
        }

        public ParenthesisImbalanceErrorException(LineCharacter character, String message) {
            super(message + " | Occurred on line number: " + character.lineNumber);
        }
    }


    private static class LineCharacter {
        char character;
        int lineNumber;

        public LineCharacter(char character, int lineNumber) {
            this.character = character;
            this.lineNumber = lineNumber;
        }
    }
}
