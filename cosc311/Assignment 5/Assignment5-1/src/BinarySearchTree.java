import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by medwar40 on 11/5/15.
 */
public class BinarySearchTree<T extends Comparable> {

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
  }

  public boolean add(T item) {
    if (root == null) { //if root is make the new node root
      root = new Node(item);
      return true;
    } else {
      Node current = root;
      Node prev = null;
      while (current != null) { //iterates until iterator is null
        if (current.data.compareTo(item) == 0) //returns false if data exists in tree
          return false;
        //iterator goes less if iterators data is greater than new item's
        else if (current.data.compareTo(item) > 0) {
          prev = current;
          current = current.left;
          //otherwise it goes right
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

  //recursive function to find data
  public boolean find(T data) {
    return find(data, root);
  }

  private boolean find(T data, Node node) {
    //if node is null return false
    if (node == null)
      return false;
      //if node data is equal return true
    else if (node.data.compareTo(data) == 0)
      return true;
    //if data is less than current node's data go left in tree
    else if (node.data.compareTo(data) > 0)
      return find(data, node.left);
    //otherwise go right
    else
      return find(data, node.right);
  }

  //recursive function to edit data
  public boolean edit(T data) {
    return edit(data, root);
  }

  private boolean edit(T data, Node node) {
    //if node is null return false
    if (node == null)
      return false;
    //if data is 'equal' then replace the node's data with new data
    else if (node.data.compareTo(data) == 0) {
      node.data = data;
      return true;
      //if data is less than current node's data go left in tree
    } else if (node.data.compareTo(data) > 0)
      return edit(data, node.left);
      //otherwise go right
    else
      return edit(data, node.right);
  }

  public boolean delete(T data) {
    Node temp = root;
    Node prev = null;

    //loops until temp is null
    while (temp != null) {
      //stops when data is matched
      if (temp.data.compareTo(data) == 0)
        break;
      //goes left if node data is greater than data to delete
      else if (temp.data.compareTo(data) > 0) {
        prev = temp;
        temp = temp.left;
        //otherwise goes right
      } else {
        prev = temp;
        temp = temp.right;
      }
    }
    //returns false if temp is null
    if (temp == null)
      return false;
    //otherwise checks how many branches the node has
    else {
      if (temp.left == null) {
        if (temp == root)
          root = temp.right;
        else if (temp == prev.left)
          prev.left = temp.right;
        else
          prev.right = temp.right;
      } else if (temp.right == null) {
        if (temp == root)
          root = temp.left;
        else if (temp == prev.left)
          prev.left = temp.left;
        else
          prev.right = temp.left;
      } else {
        Node p = temp.left;
        Node q = temp;
        while (p.right != null) {
          q = p;
          p = p.right;
        }
        temp.data = p.data;
        if (q == temp)
          q.left = p.left;
        else
          q.right = p.left;
      }
      return true; // indicates data has been deleted
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
}
