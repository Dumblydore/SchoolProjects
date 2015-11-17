import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Maurice on 11/15/2015.
 */
public class Main {
  public static final String MENU =
    "a) Add student\n" +
      "b) Delete Student\n" +
      "c) Search for student\n" +
      "d) Edit student details\n" +
      "e) Display list of students\n" +
      "f) Upload student database\n" +
      "g) Download student database\n" +
      "q) quit\n" +
      "> ";

  public static void main(String... args) {
    Scanner input = new Scanner(System.in);
    BinarySearchTree<Student> tree = new BinarySearchTree<>();
    while (true) {
      try {
        System.out.print(MENU);
        switch (input.next()) {
          case "a":
            System.out.println(
              "===============================\n" +
              "Adding new student\n" +
              "===============================");
            boolean isAdded = tree.add(createStudent(input));
            if(isAdded)
              System.out.println("Student was successfully added.");
            else
              System.out.println("StudentId already existed.");
            break;
          case "b":

            System.out.println(
              "===============================\n" +
                "deleting student\n" +
                "===============================");
            boolean isDeleted = tree.delete(createStudentFromId(input));
            if(isDeleted)
              System.out.println("Student was successfully deleted.");
            else
              System.out.println("Could not find student.");
            break;
          case "c":
            System.out.println(
              "===============================\n" +
                "Searching for student\n" +
                "===============================");
            boolean doesExist = tree.find(createStudentFromId(input));
            if(doesExist)
              System.out.println("Student exists in database.");
            else
              System.out.println("student doesn't exist in database.");
            break;
          case "d":
            System.out.println(
              "===============================\n" +
                "Editing student\n" +
                "===============================");
            boolean isEdited = tree.add(createStudent(input));
            if(isEdited)
              System.out.println("Student information was successfully edited.");
            else
              System.out.println("StudentId does not exist.");
            break;
          case "e":
            System.out.println(
              "===============================\n" +
                "Displaying database\n" +
                "===============================");
            tree.display();
            break;
          case "f":
            System.out.println(
              "===============================\n" +
                "Uploading database\n" +
                "===============================");
            System.out.println("Enter output file name> ");
            tree.upload(input.next());
            System.out.println("File uploaded");
            break;
          case "g":
            System.out.println(
              "===============================\n" +
                "Downloading database\n" +
                "===============================");
            System.out.println("Enter input file name> ");
            tree = Student.download(input.next());
            System.out.println("File downloaded");
            break;
          case "q":
            System.exit(0);
            break;
          default:
            System.out.println("Invalid command!");
            break;
        }
      } catch (Student.BadDataInsertionException e) {
        System.err.println(e.getMessage());
      }
    }
  }

  private static Student createStudent(Scanner in) throws Student.BadDataInsertionException {
    int id, age;
    String lastName;
    double gpa;
    System.out.print("Enter student's ID> ");
    id = in.nextInt();
    System.out.print("Enter student's age> ");
    age = in.nextInt();
    System.out.print("Enter student's last name> ");
    lastName = in.next();
    System.out.print("Enter student's GPA> ");
    gpa = in.nextDouble();
    return new Student(id, age, lastName, gpa);
  }

  private static Student createStudentFromId(Scanner in) throws Student.BadDataInsertionException {
    System.out.print("Enter student's ID> ");
    int id = in.nextInt();
    return new Student(id, 0, "", 0.0);
  }
}

