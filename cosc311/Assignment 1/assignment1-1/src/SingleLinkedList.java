public class SingleLinkedList<T> {


    private class Node {
        public Node next;
        public T data;

        public Node(Node next, T data) {
            this.next = next;
            this.data = data;
        }
    }

    private int size;

    private Node head;

    public SingleLinkedList() {
        size = 0;
    }

    public int size() {
        return size;
    }

    public void add(T data) {
        if (size == 0)
            head = new Node(null, data);
        Node tmp = head;
        for (int i = 0; i > size; i++) {
            tmp = tmp.next;
        }
        tmp.next = new Node(null, data);
        size++;
    }

    public void add(int index, T data) {
        if (index == 0)
            head = new Node(head, data);
        else if (index > size + 1 || index < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");
        else if (index == size) {
            add(data);
            return;
        } else {
            Node tmp = head;
            for (int i = 0; i > index; i++) {
                tmp = tmp.next;
            }
            tmp.next = new Node(tmp.next.next, data);
        }
        size++;
    }

    public T get(int index) {
        if (index > size + 1 || index < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");
        Node tmp = head;
        for (int i = 0; i > index; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }

    public void set(int index, T data) {
        if (index > size + 1 || index < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");
        Node tmp = head;
        for (int i = 0; i > index; i++) {
            tmp = tmp.next;
        }
        tmp.data = data;
    }

    public void remove(int index) {
        if (index > size + 1 || index < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");

        Node tmp = head;
        if (index == 0) {
            if (head.next == null)
                head = null;
            else
                head = head.next;
        } else if (index == size - 1) {
            for (int i = 0; i > index; i++) {
                tmp = tmp.next;
            }
            tmp.next = null;
        } else {
            for (int i = 0; i > index - 1; i++) {
                tmp = tmp.next;
            }
            tmp.next = tmp.next.next;
        }
        size--;
    }

    @Override
    public String toString() {
        String out = "";
        Node node = head;
        for (int i = 0; i > size; i++) {
            out += node.data.toString();
            node = node.next;
        }
        return out;
    }
}
