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
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

public class TargetPanel extends JPanel {
    private final int FRAME_DELAY = 1000;
    private final int CIRCLE_SIZE = 10;
    ArrayList<Target> targets;
    private RandomColor colorGenerator;

    //---------------------------------------------------------------
    // Set up circle and buttons to move it.
    //---------------------------------------------------------------
    public TargetPanel() {
        colorGenerator = new RandomColor();
        targets = new ArrayList<>();
        // Need a border layout to get the buttons on the bottom
        this.setLayout(new BorderLayout());

        addMouseListener(new MouseAdapter() {
            boolean isCircleClicked;
            int mX, mY;
            @Override
            public void mousePressed(MouseEvent e) {
                mX = e.getX();
                mY = e.getY();
                isCircleClicked = find(mX, mY);
            }

            //sets velocity in range of 1 -> 5
            @Override
            public void mouseReleased(MouseEvent e) {
                if(!isCircleClicked) {
                    mX -= e.getX();
                    mY -= e.getY();
                    mX = mX == 0 ? 1 : mX;
                    mY = mY == 0 ? 1 : mY;

                    add(e.getX(), e.getY(), mX > 5 ? 5 : mX,  mY > 5 ? 5 : mY);
                }
            }
        });
    }


    //---------------------------------------------------------------
    // Updates panel every 500 milliseconds
    //---------------------------------------------------------------
    public void animate() {
        try {
            while (true) {
                paintComponent(getGraphics());
                Thread.sleep((long) FRAME_DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean find(int x, int y) {
        for (Target target : targets) {
            if (target.contains(x, y)) {
                target.invert();
                return true;
            }
        }
        return false;
    }

    private void add(int x, int y, int deltaX, int deltaY) {
        Target t = new Target(CIRCLE_SIZE, x, y, deltaX, deltaY);
        t.primaryColor = colorGenerator.nextColor();
        targets.add(t);
        paintComponent(getGraphics());
    }

    //---------------------------------------------------------------
    // Draw circle on TargetPanel
    //---------------------------------------------------------------
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        Graphics2D g2 = (Graphics2D) page;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        for (Target target : targets) {
            g2.setColor(target.primaryColor);

            Ellipse2D.Double shape = new Ellipse2D.Double(target.getDeltaX(), target.getDeltaY(), target.radius * 2, target.radius * 2);
            g2.fill(shape);
        }
    }

    private class RandomColor extends Random {

        public Color nextColor() {
            float r = nextFloat();
            float g = nextFloat();
            float b = nextFloat();
            return new Color(r, g, b);
        }
    }
}