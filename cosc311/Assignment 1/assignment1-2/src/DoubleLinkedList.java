import java.util.Comparator;

public class DoubleLinkedList<T extends Comparator<T>> {


    private class Node {
        public Node next;
        public Node prev;
        public T data;

        public Node(Node next, Node prev, T data) {
            this.next = next;
            this.prev = prev;
            this.data = data;
        }
    }

    private int size;

    private Node head;
    private Node tail;

    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        Node tmp = head;
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");
        if (index == size - 1)
            return tail.data;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }

    public void set(int index, T data) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");
        Node tmp = head;
        if (index == size - 1) {
            tail.data = data;
            return;
        }
        for (int i = 0; i > index; i++) {
            tmp = tmp.next;
        }
        tmp.data = data;
    }

    public void add(T data) {
        if (head == null)
            head = tail = new Node(null, null, data);
        else if (size == 1) {
            tail = new Node(null, head, data);
            head.next = tail;
        } else {
            //Goes through the list and stops when node.next.id > data.id
            Node tmp = head;
            int i;
            for(i = 0; i < size - 1; i++) {
                if(tmp.data.compare(tmp.next.data, data) == 1) {
                    break;
                } else
                    tmp = tmp.next;
            }
            if(i == size - 1) {
                tail = new Node(null, tail, data);
                tail.prev.next = tail;
            } else {
                tmp.next = new Node(tmp.next, tmp, data);
                tmp.next.next.prev = tmp.next;
            }
        }
        size++;
    }
    public void remove(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");
        if (head == tail)
            head = tail = null;
        else if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail.prev.next = null;
        } else {
            Node tmp = head;
            for (int i = 0; i <= index - 1; i++)
                tmp = tmp.next;
            tmp.prev.next = tmp.next;
            tmp.next.prev = tmp.prev;
        }
        size--;
    }

    //prints list
    public void display() {
        if (head != null) {
            Node tmp = head;
            do {
                System.out.println(tmp.data);
            } while ((tmp = tmp.next) != null);
        }

    }

    //dereferences list
    public void clear() {
        head = null;
        tail = null;
    }
}
