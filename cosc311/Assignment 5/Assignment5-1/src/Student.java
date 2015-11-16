import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Student implements Comparable<Student> {
  public static final int MAX_ID = 10000;
  public static final int MAX_AGE = 100;
  public static final double MAX_GPA = 4;
  public static final double MIN_GPA = 0;

  public int id, age;
  public String lastName;
  public double gpa;

  public Student(int id, int age, String lastName, double gpa) {
    this.id = id;
    this.age = age;
    this.lastName = lastName;
    this.gpa = gpa;
  }

  @Override
  public int compareTo(Student o) {
    return id - o.id;
  }

  @Override
  public String toString() {
    return
      id +
        " " + age +
        " " + lastName +
        " " + gpa;
  }

  public static BinarySearchTree<Student> download(String filePath) {
    BinarySearchTree<Student> tree = new BinarySearchTree<>();
    Path path = FileSystems.getDefault().getPath(".", filePath);
    InputStream in = null;
    try {
      in = Files.newInputStream(path);
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      String line = "";
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(" ");
        tree.add(new Student(
          Integer.parseInt(data[0]),
          Integer.parseInt(data[1]),
          data[2],
          Double.parseDouble(data[3])));
      }
      System.out.println("Download completed.");
    } catch (IOException e) {
      e.printStackTrace();
    }

    return tree;
  }
}
