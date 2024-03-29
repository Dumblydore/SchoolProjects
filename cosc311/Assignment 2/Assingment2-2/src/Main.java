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

  public static void main(String... args) {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter file name to validate map: ");
    checkMap(keyboard.nextLine());

  }

  public static void checkMap(String filePath) {
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
    Move currentMove = moveHistory.pop();
    //'moves' in order of north east south & west
    while (true) {
      currentMove.isVisited = true;
      if (currentMove.equals(endPoint))
        return true;
      if (isSpaceAvailable('n', currentMove)) {
        moveHistory.push(currentMove);
        currentMove = map[currentMove.x][currentMove.y + 1];
      } else if (isSpaceAvailable('e', currentMove)) {
        moveHistory.push(currentMove);
        currentMove = map[currentMove.x + 1][currentMove.y];
      } else if (isSpaceAvailable('s', currentMove)) {
        moveHistory.push(currentMove);
        currentMove = map[currentMove.x][currentMove.y - 1];
      } else if (isSpaceAvailable('w', currentMove)) {
        currentMove.isVisited = true;
        moveHistory.push(currentMove);
        currentMove = map[currentMove.x - 1][currentMove.y];
      } else {
        if (moveHistory.isEmpty())
          break;
        else
          currentMove = moveHistory.pop();
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

  //class to hold spaces
  public static class Move {
    boolean isVisited, isWall;
    int x, y;

    public Move(boolean isWall, int xCoors, int yCoors) {
      this.isWall = isWall;
      x = xCoors;
      y = yCoors;
    }

    //overrides equals to compare x & y coordinates o
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Move move = (Move) o;
      return x == move.x && y == move.y;

    }
  }
}
