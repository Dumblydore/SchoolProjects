import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    protected static Move[][] map;
    static Move endMove;
    private static boolean isComplete;
    private static Stack<Move> moves;

    public static void main(String... args) {
        moves = new Stack<>();
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter filepath: ");
        String filePath = keyboard.next();
        loadMap(filePath);
        if (isComplete) {
            System.out.print("Path: ");
            while (!moves.isEmpty()) {
                System.out.print(moves.pop() + " ");
            }
        } else {
            System.out.println("Map could not be completed.");
        }
    }

    private static void loadMap(String fileName) {
        Path path = FileSystems.getDefault().getPath(".", fileName);
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
            endMove = getPoint(reader.readLine());
            reader.close();
            move(startPoint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get start & and points from line
    private static Move getPoint(String pointString) {
        String[] dimensions = pointString.split(" ");
        return new Move(false, Integer.parseInt(dimensions[1]),
                Integer.parseInt(dimensions[0]));
    }

    /*each 'movement' in the method requires the space to be available.*/
    public static void move(Move fromMove) {
        fromMove.isVisited = true;
        if (fromMove.equals(endMove)) {
            moves.add(fromMove);
            isComplete = true;
            return;
        }
        if (isSpaceAvailable('n', fromMove))
            move(map[fromMove.y + 1][fromMove.x]);
        if (isSpaceAvailable('e', fromMove))
            move(map[fromMove.y][fromMove.x + 1]);
        if (isSpaceAvailable('s', fromMove))
            move(map[fromMove.y - 1][fromMove.x]);
        if (isSpaceAvailable('w', fromMove))
            move(map[fromMove.y][fromMove.x - 1]);

        if (isComplete && fromMove.isVisited) {
            moves.add(fromMove);
        } else {
            fromMove.isVisited = false;
        }
    }

    //Checks if the space exists, a wall, if the space has been visited, and if the maze was completed.
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
            Move move = map[y][x];
            return (!(move.isVisited || move.isWall) && !isComplete);
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

        @Override
        public String toString() {
            return "(" + y + ", " + x + ")";
        }
    }
}
