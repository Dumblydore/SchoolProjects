/**
 * Created by Maurice on 9/12/2015.
 */
public class Book {
    public String title;
    public double price;
    public int soldCopies;
    public SingleLinkedList<Customer> customers;

    public Book(String title, double price, int soldCopies, SingleLinkedList<Customer> customers) {
        this.title = title;
        this.price = price;
        this.soldCopies = soldCopies;
        this.customers = customers;
    }


}
