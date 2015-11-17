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

public class CirclePanel extends JPanel {
    private final int CIRCLE_SIZE = 50;
    private final JButton left;
    private final JButton right;
    private final JButton up;
    private final JButton down;
    private int x, y;
    private Color c;


    //---------------------------------------------------------------
    // Set up circle and buttons to move it.
    //---------------------------------------------------------------
    public CirclePanel(int width, int height) {
        // Set coordinates so circle starts in middle
        x = (width / 2) - (CIRCLE_SIZE / 2);
        y = (height / 2) - (CIRCLE_SIZE / 2);

        c = Color.green;

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

        // Add listeners to the buttons
        left.addActionListener(new MoveListener(-20, 0));
        right.addActionListener(new MoveListener(20, 0));
        up.addActionListener(new MoveListener(0, -20));
        down.addActionListener(new MoveListener(0, 20));
        redBtn.addActionListener(new ColorListener(Color.red));
        blueBtn.addActionListener(new ColorListener(Color.blue));
        greenBtn.addActionListener(new ColorListener(Color.green));

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

        customColorBtn.addActionListener(e -> {
            c = JColorChooser.showDialog(null, "Choose a color", c);
            repaint();
        });
        // Add the button panel to the bottom of the main panel
        this.add(moveButtonPanel, "South");
        this.add(colorButtonPanel, "North");
        initActions();
    }


    private void initActions() {
        Action leftAction = new KeyAction("Left", left);
        Action rightAction = new KeyAction("Up", up);
        Action upAction = new KeyAction("Down", down);
        Action downAction = new KeyAction("Right", right);


        InputMap map = getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        map.put(KeyStroke.getKeyStroke("A"), "panel.left");
        map.put(KeyStroke.getKeyStroke("W"), "panel.up");
        map.put(KeyStroke.getKeyStroke("S"), "panel.down");
        map.put(KeyStroke.getKeyStroke("D"), "panel.right");

        ActionMap actionMap = getActionMap();
        actionMap.put("panel.left", leftAction);
        actionMap.put("panel.up", rightAction);
        actionMap.put("panel.down", upAction);
        actionMap.put("panel.right", downAction);

    }

    private void checkBounds() {
        if (x <= 0)
            left.setEnabled(false);
        else
            left.setEnabled(true);
        if (x + CIRCLE_SIZE >= getWidth())
            right.setEnabled(false);
        else
            right.setEnabled(true);
        if (y - CIRCLE_SIZE <= 0)
            up.setEnabled(false);
        else
            up.setEnabled(true);
        if (y + (CIRCLE_SIZE * 2) >= getHeight())
            down.setEnabled(false);
        else
            down.setEnabled(true);
    }

    //---------------------------------------------------------------
    // Draw circle on CirclePanel
    //---------------------------------------------------------------
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        page.setColor(c);
        page.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
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
        // Change x and y coordinates and repaint.
        //---------------------------------------------------------------
        public void actionPerformed(ActionEvent e) {
            x += dx;
            y += dy;
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
        // Parameters tell how what color the circle will change to.
        //---------------------------------------------------------------
        public ColorListener(int color) {
            this.color = new Color(color);
        }

        //---------------------------------------------------------------
        // Change colors and repaint.
        //---------------------------------------------------------------
        public ColorListener(Color color) {
            this.color = color;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            c = color;
            repaint();
        }
    }

    private class KeyAction extends AbstractAction {
        public KeyAction(String name, JButton move) {
            super(name);
            putValue("move", move);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ((JButton)getValue("move")).doClick();
        }
    }
}