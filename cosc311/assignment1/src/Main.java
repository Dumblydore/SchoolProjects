import java.util.Scanner;

public class Main {

    private static SingleLinkedList<Book> bookStore;

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
    private static final String MENU_ENTER_ORDER = "Please enter the number of orders> ";

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        bookStore = new SingleLinkedList<>();
        System.out.print(MENU);
        while (true) {
            switch (keyboard.next()) {
                case "add":
                    System.out.println("");
                    String bookName = keyboard.nextLine();
                    addBook(keyboard.next(), keyboard.nextDouble());
                    break;
                case "order":
                    System.out.print(MENU_ENTER_BOOK);
                    orderBook(keyboard.next(), keyboard.next(), keyboard.nextInt());
                    break;
                case "sell":
                    System.out.println("Please enter (eg book bookName)> ");
                    Book(keyboard.next(), keyboard.next());
                    break;
                case "cancel":
                    System.out.println("Please enter (eg book bookName)> ");
                    Book(keyboard.next(), keyboard.next());
                    break;
                case "quit":
                    System.out.println("Good bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command, try again.");

            }
        }
    }

    private static void addBook(String bookTitle, double bookPrice) {
        if (findBook(bookTitle) == -1)
            System.out.println("Book already exists in the store.");
        else
            bookStore.add(0, new Book(bookTitle, bookPrice));
    }

    private static void orderBook(String customerName, String bookTitle, int numberOfCopies) {
        int index;
        if ((index = findBook(bookTitle)) == -1)
            System.out.println("The book you're trying to order doesn't exist in the store, please add the book first.");
        else
            bookStore.get(index).customers.add(new Customer(customerName, numberOfCopies));
    }

    private static void sellBook(String bookTitle) {
        int index;
        Book bookToBeSold;
        if ((index = findBook(bookTitle)) != -1 && (bookToBeSold = bookStore.get(index)).customers.size() > 0) {
            bookToBeSold.soldCopies += bookToBeSold.customers.get(0).orderedCopies;
            bookToBeSold.customers.remove(0);
        }
    }

    private static void cancelOrder(String customerName, String bookTitle) {
        int bookIndex;
        int customerIndex;

        if ((bookIndex = findBook(bookTitle)) != -1 &&
                (customerIndex = findCustomer(bookStore.get(bookIndex), customerName)) != -1) {
            bookStore.get(bookIndex).customers.remove(customerIndex);
            System.out.println("Removed customer: " + customerName + "from the wait list for " + bookTitle);
        } else {
            System.out.println("Book and/or customer do not exist, try again.");
        }
    }

    private static int findCustomer(Book book, String customerName) {
        int index = 0;
        for (Customer customer : book.customers) {
            if (customer.lastName.equals(customerName))
                return index;
            index++;
        }
        return -1;
    }

    private static int findBook(String title) {
        int index = 0;
        for (Book book : bookStore) {
            if (book.title.equals(title)) {
                return index;
            }
            index++;
        }
        return -1;
    }


    public static class Customer {

        public String lastName;
        public int orderedCopies;

        public Customer(String lastName, int orderedCopies) {
            this.lastName = lastName;
            this.orderedCopies = orderedCopies;
        }
    }


    public static class Book {
        public String title;
        public double price;
        public int soldCopies;
        public SingleLinkedList<Customer> customers;

        public Book(String title, double price) {
            this.title = title;
            this.price = price;
            this.soldCopies = 0;
            this.customers = new SingleLinkedList<>();
        }

    }
}
