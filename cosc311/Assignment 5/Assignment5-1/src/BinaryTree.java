/**
 * Created by medwar40 on 11/5/15.
 */
public class BinaryTree<T extends Comparable> {

    private class Node {
        Node left;
        Node right;
        T data;

        public Node(T data) {
            this.data = data;
        }

        public Node(Node left, Node right, T data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }
    }

    private Node root;


    public BinaryTree() {
        root = null;
    }

    public boolean add(T item) {
        if (root == null) {
            root = new Node(item);
            return true;
        } else {
            Node current = root;
            Node prev = null;
            while (current != null) {
                if (current.data.compareTo(item) == 0)
                    return false;
                else if (current.data.compareTo(item) > 0) {
                    prev = current;
                    current = current.left;
                } else {
                    prev = current;
                    current = current.right;
                }
            }
            if (prev.data.compareTo(item) > 0)
                prev.left = new Node(item);
            else
                prev.right = new Node(item);
            return true;
        }
    }

    public boolean find(T data) {
        return find(data, root);
    }

    private boolean find(T data, Node node) {
        if (node == null)
            return false;
        else if (node.data.compareTo(data) == 0)
            return true;
        else if (node.data.compareTo(data) > 0)
            return find(data, node.left);
        else
            return find(data, node.right);
    }

    private void display(Node node) {
        if (node != null) {
            System.out.println(node.data.toString());
            display(node.left);
            display(node.right);
        }
    }
}
