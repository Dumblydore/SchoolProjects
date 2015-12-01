import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by medwar40 on 11/5/15.
 */
public class BinarySearchTree<T> {

    private final int CAPACITY = 10000;
    private int probes;

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


    public BinarySearchTree() {
        root = null;
        probes = 0;
    }

    public boolean add(T item) {
        if (root == null) { //if root is make the new node root
            root = new Node(item);
            return true;
        } else {
            Node current = root;
            Node prev = null;
            while (current != null) { //iterates until iterator is null
                if (current.data.hashCode() == item.hashCode()) //returns false if data exists in tree
                    return false;
                    //iterator goes less if iterators data is greater than new item's
                else if (current.data.hashCode() > item.hashCode()) {
                    prev = current;
                    current = current.left;
                    //otherwise it goes right
                } else {
                    if(!current.equals(item)) { //if the values are equal then do not update the node
                        prev = current;
                        current = current.right;
                    }
                    probes++;
                }
            }
            if (prev.data.hashCode() > item.hashCode())
                prev.left = new Node(item);
            else
                prev.right = new Node(item);
            return true;
        }
    }

    //recursive function to print tree
    public void display() {
        display(root);
    }


    private void display(Node node) {
        if (node != null) {
            display(node.left);//prints left branch
            System.out.println(node.data.toString()); //prints node
            display(node.right);//prints right branch
        }
    }

    //recursive function to write tree to file
    public void upload(String filePath) {
        try {
            Path path = FileSystems.getDefault().getPath(".", filePath);
            OutputStream in = Files.newOutputStream(path);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(in));
            upload(root, writer);
            writer.flush();
            writer.close();
            System.out.println("Upload completed.");
        } catch (IOException e) {

        }
    }

    private void upload(Node node, BufferedWriter writer) throws IOException{
        if (node != null) {
            upload(node.left, writer); //write left branch
            writer.write(node.data.toString() + '\n'); // write current node
            upload(node.right, writer); //write right branch
        }
    }


    //recursive function to get height of tree
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if(node == null) {
            return 0;
        } else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            //returns 1 + the largest of the left and right branch's height
            return 1 + (leftHeight <= rightHeight ? rightHeight : leftHeight);
        }
    }

    //recursive function to get number of nodes
    public int nodeSize() {
        return nodeSize(root);
    }

    private int nodeSize(Node node) {
        //return 0 if node is null otherwise returns branch + 1
        return node == null ? 0 : 1 + (nodeSize(node.left) + nodeSize(node.right));
    }

    public int getProbes() {
        return probes;
    }
}
