import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    protected static Move[][] map;

    public static void main(String... args) {
        String filePath = "test.txt";
        Path path = FileSystems.getDefault().getPath(".", filePath);
        InputStream in;
        try {
            in = Files.newInputStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            //Gets dimensions of map
            String line = reader.readLine();
            String[] dimensions = line.split(" ");
            int height = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);
            map = new Move[height][width];

            //Generates map
            int heightIndex = 0;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < width; i++) {
                    map[heightIndex][i] = new Move(
                            line.charAt(i) == '1',
                            i, heightIndex);
                }
                heightIndex++;
                if (heightIndex == height)
                    break;
            }
            //Gets start & end points
            Move startPoint = getPoint(reader.readLine());
            Move endPoint = getPoint(reader.readLine());

            reader.close();
            boolean isPossible = validate(startPoint, endPoint);
            System.out.println(isPossible ? "Yes" : "No");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Move getPoint(String pointString) {

        String[] dimensions = pointString.split(" ");
        return new Move(false, Integer.parseInt(dimensions[1]),
                Integer.parseInt(dimensions[0]));
    }

    private static boolean validate(Move startPoint, Move endPoint) {
        Stack<Move> moveHistory = new Stack<>();
        moveHistory.push(startPoint);
        System.out.println(moveHistory);
        Move currentMove = moveHistory.pop();
        System.out.println(moveHistory);
        while (true) {
            currentMove.isVisited = true;
            if (currentMove.equals(endPoint))
                return true;
            if (isSpaceAvailable('n', currentMove)) {
                moveHistory.push(currentMove);
                System.out.println(moveHistory);
                currentMove = map[currentMove.y + 1][currentMove.x];
            } else if (isSpaceAvailable('e', currentMove)) {
                moveHistory.push(currentMove);
                System.out.println(moveHistory);
                currentMove = map[currentMove.y][currentMove.x + 1];
            } else if (isSpaceAvailable('s', currentMove)) {
                moveHistory.push(currentMove);
                System.out.println(moveHistory);
                currentMove = map[currentMove.y - 1][currentMove.x];
            } else if (isSpaceAvailable('w', currentMove)) {
                currentMove.isVisited = true;
                moveHistory.push(currentMove);
                System.out.println(moveHistory);
                currentMove = map[currentMove.x - 1][currentMove.y];
            } else {
                if (moveHistory.isEmpty())
                    break;
                else {
                    currentMove = moveHistory.pop();
                    System.out.println(moveHistory);
                }
            }
        }
        return false;
    }

    //Checks to see if space exists has been visited
    private static boolean isSpaceAvailable(char direction, Move currentSpace) {
        int x = currentSpace.x;
        int y = currentSpace.y;
        switch (direction) {
            case 'n':
                y += 1;
                break;
            case 'e':
                x += 1;
                break;
            case 's':
                y -= 1;
                break;
            case 'w':
                x -= 1;
                break;
        }

        try {
            Move move = map[x][y];
            return !(move.isVisited || move.isWall);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static class Move {
        boolean isVisited, isWall;
        int x, y;

        public Move(boolean isWall, int xCoors, int yCoors) {
            this.isWall = isWall;
            x = xCoors;
            y = yCoors;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Move move = (Move) o;
            return x == move.x && y == move.y;

        }
    }
}
