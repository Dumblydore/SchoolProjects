import java.util.Scanner;

public class Main {
    static int[][] map;
    static int rows, columns, locationX, locationY;
    static boolean isComplete;

    public static void main(String... args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        rows = input.nextInt();
        System.out.print("Enter number of columns: ");
        columns = input.nextInt();
        System.out.print("Enter starting location: ");
        locationX = input.nextInt();
        locationY = input.nextInt();
        map = new int[rows][columns];
        move(locationX, locationY, 1);
        if(isComplete)
            printMap();
        else
            System.out.println("Could not be completed.");
    }

    public static void move(int locationX, int locationY, int move) {
        int size = rows * columns;
        map[locationX][locationY] = move;
        if (move >= size) {
            System.out.print("Output: ");
            isComplete = true;
            return;
        }
        if (isSpaceAvailable(locationX + 1, locationY + 2))
            move(locationX + 1, locationY + 2, move + 1);
        if (isSpaceAvailable(locationX + 1, locationY - 2))
            move(locationX + 1, locationY - 2, move + 1);
        if (isSpaceAvailable(locationX - 1, locationY + 2))
            move(locationX - 1, locationY + 2, move + 1);
        if (isSpaceAvailable(locationX - 1, locationY - 2))
            move(locationX - 1, locationY - 2, move + 1);
        if (isSpaceAvailable(locationX + 2, locationY + 1))
            move(locationX + 2, locationY + 1, move + 1);
        if (isSpaceAvailable(locationX + 2, locationY - 1))
            move(locationX + 2, locationY - 1, move + 1);
        if (isSpaceAvailable(locationX - 2, locationY + 1))
            move(locationX - 2, locationY + 1, move + 1);
        if (isSpaceAvailable(locationX - 2, locationY - 1))
            move(locationX - 2, locationY - 1, move + 1);
        if(!isComplete) {
            map[locationX][locationY] = 0;
        }
    }

    /*checks if the coordinates are valid, space has been visited and if board is already completed*/
    public static boolean isSpaceAvailable(int x, int y) {
        return rows > x && columns > y && x >= 0 && y >= 0 && map[x][y] == 0 && !isComplete;
    }

    public static void printMap() {
        for (int[] column : map) {
            for (int row : column) {
                System.out.print(row + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
