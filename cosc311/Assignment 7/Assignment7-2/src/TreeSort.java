import java.lang.reflect.Array;

/**
 * Created by medwar40 on 12/4/15.
 */
public class TreeSort {

    public static <V> V[] sort(V[] input) {
        return null;
    }

    private class BinarySearchTree<V> {

        private final int CAPACITY = 10000;
        private int probes;

        private class Node {
            Node left;
            Node right;
            V data;

            public Node(V data) {
                this.data = data;
            }

            public Node(Node left, Node right, V data) {
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

        public boolean add(V item) {
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
        public V[] sort(V[] in, Class<V> vClass) {
            V[] array = (V[]) Array.newInstance(vClass, nodeSize());
            sort(root, array, 0);
            return array;
        }


        private int sort(Node node, V[] array, int index) {
            if (node != null) {
                array[index] = node.data;
                int rightIndex = sort(node.left, array, index + 1);//prints left branch
                return sort(node.right, array, index + rightIndex);//prints right branch
            }
            return index;
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
}
