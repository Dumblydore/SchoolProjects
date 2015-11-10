import javax.swing.*;

/**
 * Created by medwar40 on 11/9/15.
 */
public class MoveCircleRect {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MoveCircleRect");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);

        frame.getContentPane().add(new CircleRectPanel(600, 300));
        frame.setVisible(true);
    }
}
