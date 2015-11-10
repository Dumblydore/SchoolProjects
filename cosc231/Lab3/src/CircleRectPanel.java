//Added to placate JBuilder and other IDEs
// ******************************************************************
//   CirclePanel.java
//
//   A panel with a circle drawn in the center and buttons on the 
//   bottom that move the circle.
// ******************************************************************

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CircleRectPanel extends JPanel {

    private final int SHAPE_SIZE = 50;
    private final JButton left;
    private final JButton right;
    private final JButton up;
    private final JButton down;
    private int circleX, circleY;
    private int rectX, rectY;
    private Color circleColor;
    private Color rectColor;
    private boolean affectRect = false;

    //---------------------------------------------------------------
    // Set up circle and buttons to move it.
    //---------------------------------------------------------------
    public CircleRectPanel(int width, int height) {

        // Set coordinates so rectangle starts in middle
        rectX = (width / 2) - (SHAPE_SIZE / 2);
        rectY = (height / 2) - (SHAPE_SIZE / 2);

        // Set coordinates so circle starts in middle
        circleX = (width / 2) - (SHAPE_SIZE / 2);
        circleY = (height / 2) - (SHAPE_SIZE / 2);

        circleColor = Color.green;
        rectColor = Color.blue;

        // Need a border layout to get the buttons on the bottom
        this.setLayout(new BorderLayout());

        // Create buttons to move the circle
        left = new JButton("Left");
        right = new JButton("Right");
        up = new JButton("Up");
        down = new JButton("Down");

        JButton redBtn = new JButton("Red");
        JButton blueBtn = new JButton("Blue");
        JButton greenBtn = new JButton("Green");
        JButton customColorBtn = new JButton("Choose a color");
        JCheckBox checkBox = new JCheckBox("Affect Rectangle");

        // Add listeners to the buttons
        left.addActionListener(new MoveListener(-20, 0));
        right.addActionListener(new MoveListener(20, 0));
        up.addActionListener(new MoveListener(0, -20));
        down.addActionListener(new MoveListener(0, 20));
        redBtn.addActionListener(new ColorListener(Color.red));
        blueBtn.addActionListener(new ColorListener(Color.blue));
        greenBtn.addActionListener(new ColorListener(Color.green));
        checkBox.setSelected(affectRect);
        checkBox.addActionListener(e -> {
            affectRect = !affectRect;
            checkBox.setSelected(affectRect);
            checkBounds();
        });

        // Need a panel to put the buttons on or they'll be on
        // top of each other.
        JPanel moveButtonPanel = new JPanel();
        moveButtonPanel.add(left);
        moveButtonPanel.add(right);
        moveButtonPanel.add(up);
        moveButtonPanel.add(down);

        JPanel colorButtonPanel = new JPanel();
        colorButtonPanel.add(redBtn);
        colorButtonPanel.add(blueBtn);
        colorButtonPanel.add(greenBtn);
        colorButtonPanel.add(customColorBtn);
        colorButtonPanel.add(checkBox);

        customColorBtn.addActionListener(e -> {
            if (affectRect)
                rectColor = JColorChooser.showDialog(null, "Choose a color", rectColor);
            else
                circleColor = JColorChooser.showDialog(null, "Choose a color", circleColor);
            repaint();
        });
        // Add the button panel to the bottom of the main panel
        this.add(moveButtonPanel, "South");
        this.add(colorButtonPanel, "North");
    }


    private void checkBounds() {
        int boundX = affectRect ? rectX : circleX;
        int boundY = affectRect ? rectY : circleY;

        if (boundX <= 0)
            left.setEnabled(false);
        else
            left.setEnabled(true);
        if (boundX + SHAPE_SIZE >= getWidth())
            right.setEnabled(false);
        else
            right.setEnabled(true);
        if (boundY - SHAPE_SIZE <= 0)
            up.setEnabled(false);
        else
            up.setEnabled(true);
        if (boundY + (SHAPE_SIZE * 2) >= getHeight())
            down.setEnabled(false);
        else
            down.setEnabled(true);
    }

    //---------------------------------------------------------------
    // Draw circle on CirclePanel
    //---------------------------------------------------------------
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        page.setColor(rectColor);
        page.fillRect(rectX, rectY, SHAPE_SIZE, SHAPE_SIZE);
        page.setColor(circleColor);
        page.fillOval(circleX, circleY, SHAPE_SIZE, SHAPE_SIZE);
        page.setColor(Color.black);
        page.drawOval(circleX, circleY, SHAPE_SIZE, SHAPE_SIZE);
    }

    //---------------------------------------------------------------
    // Class to listen for button clicks that move circle.
    //---------------------------------------------------------------
    private class MoveListener implements ActionListener {
        private int dx;
        private int dy;

        //---------------------------------------------------------------
        // Parameters tell how to move circle at click.
        //---------------------------------------------------------------
        public MoveListener(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        //---------------------------------------------------------------
        // Change circleX and circleY coordinates and repaint.
        //---------------------------------------------------------------
        public void actionPerformed(ActionEvent e) {
            if (affectRect) {
                rectX += dx;
                rectY += dy;
            } else {
                circleX += dx;
                circleY += dy;
            }
            checkBounds();
            repaint();
        }
    }


    //---------------------------------------------------------------
    // Class to listen for button clicks that change the color of the circle.
    //---------------------------------------------------------------
    private class ColorListener implements ActionListener {

        Color color;

        //---------------------------------------------------------------
        // Change colors and repaint.
        //---------------------------------------------------------------
        public ColorListener(Color color) {
            this.color = color;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (affectRect)
                rectColor = color;
            else
                circleColor = color;
            repaint();
        }
    }

}