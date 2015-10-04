import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static DoubleLinkedList<Student> studentStore;

    private static final String MENU = "Welcome to the bookstore! - Main menu\n" +
            "**Commands**\n" +
            "a -> Add student\n" +
            "b -> Remove student\n" +
            "c -> Search for student \n" +
            "d -> Display students\n" +
            "e -> Save student list\n" +
            "f -> Load student list from file \n" +
            "q -> Quit\n" +
            ">";
    private static final String MENU_ENTER_ID = "Please enter student's ID> ";
    private static final String MENU_ENTER_NAME = "Please enter student's name> ";
    private static final String MENU_ENTER_AGE = "Please enter student's age > ";
    private static final String MENU_ENTER_GPA = "Please enter student's GPA > ";
    private static final String MENU_ENTER_FILE = "Please enter the file path> ";

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        studentStore = new DoubleLinkedList<>();

        while (true) {
            String studentName;
            int id, age, index;
            double gpa;
            String filePath;

            System.out.print(MENU);
            switch (keyboard.next()) {
                case "a":
                    System.out.print(MENU_ENTER_ID);
                    id = keyboard.nextInt();
                    if(findStudent(id) != -1) {
                        System.out.println("Existing id choose another!");
                        break;
                    }
                    System.out.print(MENU_ENTER_NAME);
                    studentName = keyboard.next();
                    System.out.print(MENU_ENTER_AGE);
                    age = keyboard.nextInt();
                    System.out.print(MENU_ENTER_GPA);
                    gpa = keyboard.nextDouble();
                    studentStore.add(new Student(id, studentName, age, gpa));
                    break;
                case "b":
                    System.out.print(MENU_ENTER_ID);
                    id = keyboard.nextInt();
                    if ((index = findStudent(id)) != -1) {
                        studentStore.remove(index);
                        System.out.println("StudentId " + id + " removed");
                    } else
                        System.out.println("Student ID not found");
                    break;
                case "c":
                    System.out.print(MENU_ENTER_ID);
                    id = keyboard.nextInt();
                    if ((index = findStudent(id)) != -1) {
                        System.out.println("Student: " + studentStore.get(index));
                    } else
                        System.out.println("Student ID not found");
                    break;
                case "d":
                        studentStore.display();
                    break;
                case "e":
                    System.out.print(MENU_ENTER_FILE);
                    try {
                        writeStudentFile(keyboard.next(), studentStore);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "f":
                    System.out.print(MENU_ENTER_FILE);
                    try {
                        readStudentFromFile(keyboard.next(), studentStore);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "q":
                    System.out.println("Exiting");
                    System.exit(0);
                default:
                    System.out.println("Invalid command");
            }
        }
    }


    private static int findStudent(int studentId) {
        for (int i = 0; i < studentStore.size(); i++) {
            Student student = studentStore.get(i);
            if (student.id == studentId) {
                return i;
            }
        }
        return -1;
    }

    private static void readStudentFromFile(String filePath, DoubleLinkedList<Student> students) throws IOException {
        students.clear();
        Path path = FileSystems.getDefault().getPath(".", filePath);
        InputStream in = Files.newInputStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(" | ");
            System.out.println(Arrays.toString(values));
            students.add(new Student(Integer.parseInt(values[0]), values[2], Integer.parseInt(values[4]), Double.parseDouble(values[6])));
        }
        reader.close();
    }

    private static void writeStudentFile(String filePath, DoubleLinkedList<Student> students) throws IOException {
        Path path = FileSystems.getDefault().getPath(".", filePath);
        OutputStream in = Files.newOutputStream(path);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(in));
        for(int i = 0; i < students.size(); i++)
            writer.write(students.get(i).toString());
        writer.flush();
        writer.close();
    }

    public static class Student implements Comparator<Student> {
        public static final int MAX_STUDENT_ID = 1000000;

        int id, age;
        String name;
        double gpa;

        public Student(int id, String name, int age, double gpa) {
            this.id = id;
            this.age = age;
            this.name = name;
            this.gpa = gpa;
        }

        @Override
        //Returns 1 if student1.id is greater than student2.id
        public int compare(Student student1, Student student2) {
            return (student1.id > student2.id) ? 1 : 0;
        }

        @Override
        public String toString() {
            return id + " | " + name + " | " + age + " | " + gpa;
        }
    }
}
