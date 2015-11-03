/**
 * Created by medwar40 on 10/30/15.
 */
public class RecursiveSinglyLinkedList {

    private class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    Node head;

    public RecursiveSinglyLinkedList() {
        head = new Node(0, null);
        Node tmp = head;

        for (int i = 0; i < 20; i++) {
            tmp.next = new Node(i, null);
            tmp = tmp.next;
        }
    }

    public void display() {
        display(head);
    }

    private void display(Node node) {
        System.out.println(node.data);
        if (node.next == null)
            return;
        display(node.next);
    }

    public void displayReverse() {
        displayReverse(head);
    }

    private void displayReverse(Node node) {
        if (node.next == null) {
            System.out.println(node.data);
            return;
        }
        displayReverse(node.next);
        System.out.println(node.data);
    }

    public int sum() {
        return sum(head, 0);
    }

    private int sum(Node node, int sum) {
        if (node.next == null)
            return sum;
        return sum(node.next, sum += node.data);
    }

    public void containsZero() {
        System.out.println(contains(0, head) ? "List contains zeros." : "List doesn't contain zeros.");
    }

    private boolean contains(int n, Node node) {
        if (node.next == null) {
            return node.data != n;
        }
        return node.data == n || contains(n, node);
    }

    public void printOdd() {
        printOdd(head);
    }

    private void printOdd(Node node) {
        if (node.data % 2 > 0)
            System.out.println(node.data);
        if (node.next == null)
            return;
        printOdd(node.next);
    }

    public void skip() {
        skip(false, head);
    }

    private void skip(boolean skip, Node node) {
        if (node.next == null)
            return;
        if (!skip)
            System.out.println(node.data);
        skip(!skip, node.next);
    }

}
