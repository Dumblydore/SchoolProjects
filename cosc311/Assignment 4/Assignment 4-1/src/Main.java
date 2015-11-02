import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Stack;

public class Main {

    protected static Move[][] map;
    static Move endMove;
    private static boolean isComplete;

    public static void main(String... args) {
        String filePath = "test";
        Path path = FileSystems.getDefault().getPath(".", filePath);
        InputStream in;
        File file = new File("./");
        File[] list = file.listFiles();
        if(list!=null)
            for (File fil : list) {
//                System.out.println(fil.getPath());
            }
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
            System.out.println(isComplete ? "Yes" : "No");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Move getPoint(String pointString) {

        String[] dimensions = pointString.split(" ");
        return new Move(false, Integer.parseInt(dimensions[1]),
                Integer.parseInt(dimensions[0]));
    }

    public static void move(Move fromMove) {
        fromMove.isVisited = true;
        printMap();
        if (fromMove.equals(endMove) || isComplete) {
            System.out.println("complete!");
            isComplete = true;
            return;
        }
        if (isSpaceAvailable('n', fromMove))
            move(map[fromMove.x][fromMove.y + 1]);
        if (isSpaceAvailable('e', fromMove))
            move(map[fromMove.x + 1][fromMove.y]);
        if (isSpaceAvailable('s', fromMove))
            move(map[fromMove.x][fromMove.y - 1]);
        if (isSpaceAvailable('w', fromMove))
            move(map[fromMove.x - 1][fromMove.y]);

        if (!isComplete) {
            fromMove.isVisited = false;
        }
    }

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

    public static void printMap() {
        for (Move[] column : map) {
            for (Move row : column) {
                System.out.print(row + " ");
            }
            System.out.println();
        }
        System.out.println();
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
            return isWall ? "1" : isVisited ? "v" : "0";
        }
    }
}
