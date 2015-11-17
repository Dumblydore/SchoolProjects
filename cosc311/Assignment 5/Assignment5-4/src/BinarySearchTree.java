/**
 * Created by medwar40 on 11/5/15.
 */
public class BinarySearchTree {

  private class Node {
    Node left;
    Node right;
    int data;

    public Node(int data) {
      this.data = data;
    }

    public Node(Node left, Node right, int data) {
      this.left = left;
      this.right = right;
      this.data = data;
    }
  }

  private Node root;


  public BinarySearchTree(int[] numbers) {
    root = null;
    for (int i : numbers)
      add(i);
  }

  public boolean add(int item) {
    if (root == null) { //if root is make the new node root
      root = new Node(item);
      return true;
    } else {
      Node current = root;
      Node prev = null;
      while (current != null) { //iterates until iterator is null
        if (current.data == item) //returns false if data exists in tree
          return false;
          //iterator goes less if iterators data is greater than new item's
        else if (current.data > item) {
          prev = current;
          current = current.left;
          //otherwise it goes right
        } else {
          prev = current;
          current = current.right;
        }
      }
      if (prev.data > item)
        prev.left = new Node(item);
      else
        prev.right = new Node(item);
      return true;
    }
  }

  //recursive function to find data
  public boolean find(int data) {
    return find(data, root);
  }

  private boolean find(int data, Node node) {
    //if node is null return false
    if (node == null)
      return false;
      //if node data is equal return true
    else if (node.data == data) {
      System.out.print(node.data);
      return true;
      //if data is less than current node's data go left in tree
    } else if (node.data > data) {
      System.out.print(node.data + " > ");
      return find(data, node.left);
      //otherwise go right
    } else {
      System.out.print(node.data + " > ");
      return find(data, node.right);
    }
  }

  //specific recursive function to find zero
  public boolean findZero() {
    return findZero(0, root);
  }

  private boolean findZero(int data, Node node) {
    //if node is null return false
    if (node == null)
      return false;
      //if node data is equal return true
    else if (node.data == data) {
      return true;
      //if data is less than current node's data go left in tree
    } else if (node.data > data) {
      return find(data, node.left);
      //otherwise go right
    } else {
      return find(data, node.right);
    }
  }

  //recursive function to edit data
  public boolean edit(int data) {
    return edit(data, root);
  }

  private boolean edit(int data, Node node) {
    //if node is null return false
    if (node == null)
      return false;
      //if data is 'equal' then replace the node's data with new data
    else if (node.data == data) {
      node.data = data;
      return true;
      //if data is less than current node's data go left in tree
    } else if (node.data > data)
      return edit(data, node.left);
      //otherwise go right
    else
      return edit(data, node.right);
  }

  public boolean delete(int data) {
    Node temp = root;
    Node prev = null;

    //loops until temp is null
    while (temp != null) {
      //stops when data is matched
      if (temp.data == data)
        break;
        //goes left if node data is greater than data to delete
      else if (temp.data > data) {
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

  //recursive function to get minimum value
  public int getMinimumValue() {
    return getMinimumValue(root);
  }

  private int getMinimumValue(Node node) {
    //goes left in tree until it falls off
    if (node.left != null)
      getMinimumValue(node.left);
    return node.data;
  }

  //recursive function to print tree in ascending  order
  public void displayAscendingEven() {
    displayAscendingEven(root);
  }

  private void displayAscendingEven(Node node) {
    if (node != null) {
      displayAscendingEven(node.left);//prints left branch
      if((node.data % 2) == 0) //checks to see if number is even
        System.out.println(node.data); //prints node
      displayAscendingEven(node.right);//prints right branch
    }
  }


  public void displayDescending() {
    displayDescending(root);
  }


  private void displayDescending(Node node) {
    if (node != null) {
      displayDescending(node.right);//prints right branch
      System.out.println(node.data); //prints node
      displayDescending(node.left);//prints left branch
    }
  }

  public void displayLeaves() {
    displayLeaves(root);
  }


  private void displayLeaves(Node node) {
    if (node != null) {
      //checks for branches
      if (node.left != null || node.right != null) {
        displayLeaves(node.left);//prints left branch
        displayLeaves(node.right);//prints right branch
      } else {
        System.out.println(node.data); //prints node
      }
    }
  }

  //recursive function to get odd numbers in tree
  public int sumOdd() {
    return sumOdd(root, 0);
  }

  private int sumOdd(Node node, int sum) {
    //if node is null return 0
    if(node == null)
      return 0;
    else {
      //if node is even make number 0 otherwise use node data
      int oddNum = (node.data % 2) > 0 ? node.data : 0;
      return sum + oddNum + sumOdd(node.left, 0) + sumOdd(node.right, 0);
    }
  }

  //recursive function to get height of tree
  public int height() {
    return height(root);
  }

  private int height(Node node) {
    if (node == null) {
      return 0; //returns 0 if node is null
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
