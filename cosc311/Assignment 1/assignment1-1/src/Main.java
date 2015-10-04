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
    private static final String MENU_ENTER_ORDER = "Please enter the number of copies> ";

    public static void main(String[] args) {
        String bookTitle, customerName;
        int orderSize;
        double price;
        Scanner keyboard = new Scanner(System.in);
        bookStore = new SingleLinkedList<>();
        while (true) {
            System.out.print(MENU);
            switch (keyboard.next()) {
                case "add":
                    System.out.print(MENU_ENTER_BOOK);
                    keyboard.nextLine();
                    bookTitle = keyboard.nextLine();
                    System.out.print(MENU_ENTER_PRICE);
                    price = keyboard.nextDouble();
                    addBook(bookTitle, price);
                    break;
                case "order":
                    System.out.print(MENU_ENTER_CUSTOMER);
                    customerName = keyboard.next();
                    System.out.print(MENU_ENTER_BOOK);
                    keyboard.nextLine();
                    bookTitle = keyboard.nextLine();
                    System.out.print(MENU_ENTER_ORDER);
                    orderSize = keyboard.nextInt();
                    orderBook(customerName, bookTitle, orderSize);
                    break;
                case "sell":
                    System.out.print(MENU_ENTER_BOOK);
                    sellBook(keyboard.next());
                    break;
                case "cancel":
                    System.out.print(MENU_ENTER_CUSTOMER);
                    customerName = keyboard.next();
                    System.out.print(MENU_ENTER_BOOK);
                    keyboard.nextLine();
                    bookTitle = keyboard.nextLine();
                    cancelOrder(customerName, bookTitle);
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
        if (findBook(bookTitle) == -1) {
            bookStore.add(0, new Book(bookTitle, bookPrice));
            System.out.println(bookStore.get(0).title + " added to store!");
        } else
            System.out.println("Book already exists in the store.");
    }

    private static void orderBook(String customerName, String bookTitle, int numberOfCopies) {
        int index;
        if ((index = findBook(bookTitle)) == -1)
            System.out.println("The book you're trying to order doesn't exist in the store, please add the book first.");
        else {
            System.out.println(index);
            bookStore.get(index).customers.add(new Customer(customerName, numberOfCopies));
            System.out.println("Ordering " + numberOfCopies + " books for " + customerName);
        }
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
            System.out.println("Removed customer: " + customerName + " from the wait list for " + bookTitle);
        } else {
            System.out.println("Book and/or customer do not exist, try again.");
        }
    }

    private static int findCustomer(Book book, String customerName) {
        int index = 0;
        for (int i = 0; i < book.customers.size(); i++) {
            Customer customer = book.customers.get(i);
            if (customer.lastName.equals(customerName))
                return index;
            index++;
        }
        return -1;
    }

    private static int findBook(String title) {
        int index = 0;
        for (int i = 0; i < bookStore.size(); i++) {
            Book book = bookStore.get(i);
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
