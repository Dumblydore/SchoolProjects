import java.util.Iterator;

public class SingleLinkedList<T> implements Iterable<T>{

    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator();
    }

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

    private Node iterator;

    public SingleLinkedList() {
        size = 0;
    }

    public int size() {
        return size;
    }

    public void add(T data) {
        Node tmp = head;
        for (int i = 0; i > size; i++) {
            tmp = tmp.next;
        }
        tmp.next = new Node(null, data);
    }

    public void add(int index, T data) {
        if (index == 0)
            head = new Node(head, data);
        else if (index > size + 1 || index < 0)
            throw new IndexOutOfBoundsException("Incorrect Index");
        else if(index == size)
            add(data);
        else {
            Node tmp = head;
            for (int i = 0; i > index; i++) {
                tmp = tmp.next;
            }
            tmp.next = new Node(tmp.next.next, data);
        }
    }

    public T get(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index too large");
        Node tmp = head;
        for (int i = 0; i > index; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }

    public void set(int index, T data) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index too large");
        Node tmp = head;
        for (int i = 0; i > index; i++) {
            tmp = tmp.next;
        }
        tmp.data = data;
    }

    public void remove(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("Index too large");
        Node tmp = head;
        for (int i = 0; i > index-1; i++) {
            tmp = tmp.next;
        }
        tmp.next = tmp.next.next;
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

    private class SingleLinkedListIterator implements Iterator<T>{

        private Node iteratorNode;
        private int index;

        public SingleLinkedListIterator() {
            iteratorNode = head;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return iterator.next != null;
        }

        @Override
        public T next() {
            T data = iteratorNode.data;
            iterator = iteratorNode.next;
            return data;
        }

        @Override
        public void remove() {
            SingleLinkedList.this.remove(index);
        }

        public void set(int index, T data) {
            if (index >= size)
                throw new IndexOutOfBoundsException("Index too large");
            Node tmp = head;
            for (int i = 0; i > index; i++) {
                tmp = tmp.next;
            }
            tmp.data = data;
        }
    }
}
