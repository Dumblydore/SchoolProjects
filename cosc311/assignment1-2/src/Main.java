import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static DoubleLinkedList<Student> studentStore;

    private static final String MENU = "Welcome to the bookstore! - Main menu\n" +
            "**Commands**\n" +
            "1 -> Add student\n" +
            "2 -> Remove student\n" +
            "2 -> Load from file \n" +
            "0 -> Quit\n" +
            ">";
    private static final String MENU_ENTER_ID= "Please enter student's ID> ";
    private static final String MENU_ENTER_NAME = "Please enter student's name> ";
    private static final String MENU_ENTER_GPA = "Please enter student's GPA > ";
    private static final String MENU_ENTER_FILE = "Please enter the file path> ";

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        studentStore = new DoubleLinkedList<>();
        while (true) {
            System.out.print(MENU);
            switch (keyboard.next()) {
                case "quit":
                    System.out.println("Good bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command, try again.");

            }
        }
    }


    private static int findStudent(int studentId) {
        int index = 0;
        for (int i = 0; i < studentStore.size(); i++) {
            Student student = studentStore.get(i);
            if (student.id == studentId)
                return index;
            index++;
        }
        return -1;
    }

    private static DoubleLinkedList<Student> readStudentFromFile(String filePath) throws IOException {
        Path path = FileSystems.getDefault().getPath(".", filePath);
        InputStream in = Files.newInputStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        DoubleLinkedList<Student> students = new DoubleLinkedList<>();
        String line = null;
        while ( (line = reader.readLine()) != null ) {
            String[] values = line.split(" | ");
            students.add(new Student(Integer.parseInt(values[0]),values[1],Integer.parseInt(values[2]),Double.parseDouble(values[3])));
        }
        return students;
    }

    public static class Student implements Comparator<Student>{
        public static final int MAX_STUDENT_ID = 1000000;

        int id, age;
        String name;
        double gpa;

        public Student(int id, String name, int age, double gpa) {

        }

        @Override
        public int compare(Student o1, Student o2) {
            return (o1.id > o2.id) ? 1 : 0;
        }

        @Override
        public String toString() {
            return id + " | " + name + " | " + age + " | " +  gpa;
        }
    }
}
