import javax.swing.*;

/**
 * Created by medwar40 on 11/11/15.
 */
public class MovingTarget {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MovingTarget");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        TargetPanel panel = new TargetPanel();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
        panel.animate();
    }
}
