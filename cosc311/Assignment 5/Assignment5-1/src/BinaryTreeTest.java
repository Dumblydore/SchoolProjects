import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Maurice on 11/15/2015.
 */
public class BinaryTreeTest {

  private static final String[] NAMES = {
    "Deshazo",
    "Loflin",
    "Willoughby",
    "Murrieta",
    "Corona",
    "Smit",
    "Burney",
    "Arvizo",
    "Goodin",
    "Eichner",
    "Bench",
    "Sliger",
    "Westbrook",
    "Gran",
    "Shattuck",
    "Mckeon",
    "Orchard",
    "Haygood",
    "Palacios",
    "Rowles",
    "Culver",
    "Nyquist",
    "Bradt",
    "Edelman",
    "Dabney",
    "Gram",
    "Rayfield",
    "Hynes",
    "Kratz",
    "Bunce",
    "Waddell",
    "Debellis",
    "Leyendecker",
    "Wiemer",
    "Tomberlin",
    "Dilorenzo",
    "Lutz",
    "Redondo",
    "Hayashida",
    "Domer",
    "Dibenedetto",
    "Guida",
    "Waites",
    "Rebelo",
    "Tansey",
    "Gazda",
    "Townsend",
    "Herald",
    "Guzik",
    "Esquilin"};

  public static void main(String... args) {
    System.out.println("===============================\n" +
      "running testInsertion()\n" +
      "===============================");
    testInsertion();
    System.out.println("===============================\n" +
      "running testDisplay()\n" +
      "===============================");
    testDisplay();
    System.out.println("===============================\n" +
      "running testUpload()\n" +
      "===============================");
    testUpload();
    System.out.println("===============================\n" +
      "running testDownload()\n" +
      "===============================");
    testDownload();
    System.out.println("===============================\n" +
      "running testEdit()\n" +
      "===============================");
    testEdit();
  }

  private static void testInsertion() {
    List<Student> expectedStudents = new ArrayList<>();
    BinarySearchTree<Student> actualStudents = new BinarySearchTree<>();
    Random rand = new Random(37);

    for (String name : NAMES) {
      Student student = new Student(
        rand.nextInt(Student.MAX_ID),
        rand.nextInt(Student.MAX_AGE),
        name,
        rand.nextInt(5));
      expectedStudents.add(student);
      actualStudents.add(student);
    }

    for (Student expectedStudent : expectedStudents) {
      System.out.println(actualStudents.find(expectedStudent) ? "Student exists" : "Student not found!");
    }
  }

  private static void testDisplay() {
    BinarySearchTree<Student> actualStudents = new BinarySearchTree<>();
    Random rand = new Random(37);

    for (String name : NAMES) {
      Student student = new Student(
        rand.nextInt(Student.MAX_ID),
        rand.nextInt(Student.MAX_AGE),
        name,
        rand.nextInt(4));
      actualStudents.add(student);
    }
    actualStudents.display();
  }

  private static void testUpload() {
    BinarySearchTree<Student> actualStudents = new BinarySearchTree<>();
    Random rand = new Random(37);

    for (String name : NAMES) {
      Student student = new Student(
        rand.nextInt(Student.MAX_ID),
        rand.nextInt(Student.MAX_AGE),
        name,
        rand.nextInt(4));
      actualStudents.add(student);
    }
    actualStudents.download("test.txt");
  }

  private static void testDownload() {
    BinarySearchTree<Student> expectedStudents = new BinarySearchTree<>();
    Random rand = new Random(37);

    for (String name : NAMES) {
      Student student = new Student(
        rand.nextInt(Student.MAX_ID),
        rand.nextInt(Student.MAX_AGE),
        name,
        rand.nextInt(4));
      expectedStudents.add(student);
    }
    expectedStudents.download("test.txt");
    BinarySearchTree<Student> actualStudents = Student.download("test.txt");
    System.out.println(actualStudents.equals(expectedStudents) ? "Trees are equal" : "Trees are not equal");
  }

  private static void testEdit() {
    BinarySearchTree<Student> expectedStudents = new BinarySearchTree<>();
    Random rand = new Random(37);
    int maxID = 0;

    for (String name : NAMES) {
      int id = rand.nextInt(Student.MAX_ID);
      maxID = id > maxID ? id : maxID;
      Student student = new Student(
        id,
        rand.nextInt(Student.MAX_AGE),
        name,
        rand.nextInt(4));
      expectedStudents.add(student);
    }
    System.out.println("Tree height: " + expectedStudents.height());
    System.out.println("Name size: " + NAMES.length + " tree size " + expectedStudents.nodeSize());
  }
}

