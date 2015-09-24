import java.util.Scanner;

public class Main {

    private static SingleLinkedList<Student> studentStore;

    private static final String MENU = "Welcome to the bookstore! - Main menu\n" +
            "**Commands**\n" +
            "add -> add book menu\n" +
            "order -> order book menu\n" +
            "sell -> sell menu\n" +
            "cancel -> cancel order menu\n" +
            "quit -> Quit\n" +
            ">";
    private static final String MENU_ENTER_BOOK = "Please enter title of book> ";
    private static final String MENU_ENTER_CUSTOMER = "Please enter customer's last name> ";
    private static final String MENU_ENTER_PRICE = "Please enter the price of the book> ";
    private static final String MENU_ENTER_ORDER = "Please enter the number of copies> ";

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        studentStore = new SingleLinkedList<>();
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

    public static class Student {
        public static final int MAX_STUDENT_ID = 1000000;

        int id, age;
        String name;
        double gpa;

        public Student(int id, String name, int age, double gpa) {

        }
    }
}
