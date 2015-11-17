//Added to placate JBuilder and other IDEs
// ******************************************************************
//   TargetPanel.java
//
//   A panel with a circle drawn in the center and buttons on the 
//   bottom that move the circle.
// ******************************************************************

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TargetPanel extends JPanel {
    private final int CIRCLE_SIZE = 50;
    ArrayList<Target> targets;
    private int x, y;
    private Color c;


    //---------------------------------------------------------------
    // Set up circle and buttons to move it.
    //---------------------------------------------------------------
    public TargetPanel(int width, int height) {
        // Set coordinates so circle starts in middle
        x = (width / 2) - (CIRCLE_SIZE / 2);
        y = (height / 2) - (CIRCLE_SIZE / 2);
        c = Color.green;
        targets = new ArrayList<>();
        // Need a border layout to get the buttons on the bottom
        this.setLayout(new BorderLayout());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mX = e.getX(), mY = e.getY();
                Target current = find(mX, mY);
                System.out.println("test");
                if(current == null) {
                    add(x, y);
                }
            }
        });

    }

    private Target find(int x, int y) {
        for(Target target : targets) {
            if(target.contains(x, y)) {
                return target;
            }
        }
        return null;
    }

    private void add(int x, int y) {
        Target t = new Target(CIRCLE_SIZE, x, y, 10, 10);
        targets.add(t);
        paintComponent(getGraphics(), t);
    }

    //---------------------------------------------------------------
    // Draw circle on TargetPanel
    //---------------------------------------------------------------
    public void paintComponent(Graphics page, Target target) {
        super.paintComponent(page);
        System.out.println("adding new target");
        page.setColor(target.primaryColor);
        page.fillOval(target.centerX - target.radius, target.centerY - target.radius, target.radius * 2, target.radius * 2);
    }
}