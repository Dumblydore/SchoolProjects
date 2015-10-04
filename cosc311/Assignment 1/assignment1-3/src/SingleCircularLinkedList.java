public class SingleCircularLinkedList {

    private class Node {
        public Node next;
        public Integer data;

        public Node(Node next, Integer data) {
            this.next = next;
            this.data = data;
        }
    }

    private int size;

    private Node head;

    public SingleCircularLinkedList(int size) {
        for(int i = 0; i < size; i++) {
            add(i);
        }
    }

    public int size() {
        return size;
    }

    public void add(int index) {
        if (index > size + 1 || index < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");

        if (index == 0)
            head = new Node(head, index + 1);
        else {
            Node tmp = head;
            for (int i = 0; i < size-1; i++) {
                tmp = tmp.next;
            }
            tmp.next = new Node(head, index + 1);
        }
        size++;
    }

    public Integer get(int index) {
        if (index > size + 1 || index < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");
        Node tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }

    private Node getNode(int index) {
        if (index > size + 1 || index < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");
        Node tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp;
    }

    public Integer circulate(int start, int counter) {
        Node tmp = getNode(start);
        if (start > size + 1 || start < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");
        while (size > 1) {
            for (int i = 0; i < counter; i++) {
                tmp = tmp.next;
            }
                tmp = tmp.next;
                tmp = tmp.next;
            size--;
            System.out.println("Player " + tmp.next.data + " removed");
        }
        return tmp.data;
    }
    public void set(int index, Integer data) {
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
