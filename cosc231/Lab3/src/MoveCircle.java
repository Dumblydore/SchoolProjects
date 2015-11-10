//Added to placate JBuilder and other IDEs
// ******************************************************************
//   MoveCircle.java
//
//   Uses CirclePanel to display a GUI that lets the user move
//   a circle by pressing buttons.
// ******************************************************************

import javax.swing.*;


public class MoveCircle {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MoveCircle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        frame.getContentPane().add(new CirclePanel(300, 300));
        frame.setVisible(true);
    }
}