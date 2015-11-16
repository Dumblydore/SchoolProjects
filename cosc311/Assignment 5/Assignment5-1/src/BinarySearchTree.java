import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

  public boolean edit(T data) {
    return edit(data, root);
  }

  private boolean edit(T data, Node node) {
    if (node == null)
      return false;
    else if (node.data.compareTo(data) == 0) {
      node.data = data;
      return true;
    } else if (node.data.compareTo(data) > 0)
      return edit(data, node.left);
    else
      return edit(data, node.right);
  }

  public boolean delete(T data) {
    Node temp = root;
    Node prev = null;

    while (temp != null) {
      if (temp.data.compareTo(data) == 0)
        break;
      else if (temp.data.compareTo(data) > 0) {
        prev = temp;
        temp = temp.left;
      } else {
        prev = temp;
        temp = temp.right;
      }
    }
    if (temp == null)
      return false;
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
      return true;
    }
  }

  public void display() {
    display(root);
  }

  private void display(Node node) {
    if (node != null) {
      System.out.println(node.data.toString());
      display(node.left);
      display(node.right);
    }
  }

  public void download(String filePath) {
    try {
      Path path = FileSystems.getDefault().getPath(".", filePath);
      OutputStream in = Files.newOutputStream(path);
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(in));
      download(root, writer);
      writer.flush();
      writer.close();
      System.out.println("Upload completed.");
    } catch (IOException e) {

    }
  }

  private void download(Node node, BufferedWriter writer) throws IOException{
    if (node != null) {
      writer.write(node.data.toString() + '\n');
      download(node.left, writer);
      download(node.right, writer);
    }
  }



  public int height() {
    return height(root);
  }

  private int height(Node node) {
    if(node == null) {
      return 0;
    } else {
      int leftHeight = height(node.left);
      int rightHeight = height(node.right);
      return 1 + (leftHeight <= rightHeight ? rightHeight : leftHeight);
    }
  }

  public int nodeSize() {
    return nodeSize(root);
  }

  private int nodeSize(Node node) {
    return node == null ? 0 : 1 + (nodeSize(node.left) + nodeSize(node.right));
  }
}
