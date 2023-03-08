import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RandomCirclesAndLines extends JFrame implements ActionListener {

    private JTextField numCirclesField;
    private JTextField numPairsField;
    private JButton randPointButton;
    private JButton randPairButton;
    private JPanel drawPanel;

    private ArrayList<Circle> circles;
    private ArrayList<Line> lines;

    public RandomCirclesAndLines() {
        setTitle("Random Circles and Lines");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        // create a JPanel to hold the drawing
        drawPanel = new JPanel();
        add(drawPanel, BorderLayout.CENTER);

        // create a panel to hold the text fields and buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // create a text field for the number of circles
        JLabel numCirclesLabel = new JLabel("Number of circles: ");
        numCirclesField = new JTextField(5);
        controlPanel.add(numCirclesLabel);
        controlPanel.add(numCirclesField);

        // create a button to generate random points
        randPointButton = new JButton("Rand Point");
        randPointButton.addActionListener(this);
        controlPanel.add(randPointButton);

        // create a text field for the number of pairs
        JLabel numPairsLabel = new JLabel("Number of pairs: ");
        numPairsField = new JTextField(5);
        controlPanel.add(numPairsLabel);
        controlPanel.add(numPairsField);

        // create a button to generate random pairs
        randPairButton = new JButton("Rand Pair");
        randPairButton.addActionListener(this);
        controlPanel.add(randPairButton);

        // add the control panel to the frame
        add(controlPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    // handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == randPointButton) {
            // get the number of circles from the text field
            int numCircles = Integer.parseInt(numCirclesField.getText());

            // clear the panel and lists
            drawPanel.removeAll();
            circles = new ArrayList<>();
            lines = new ArrayList<>();

            // generate random circles
            for (int i = 0; i < numCircles; i++) {
                int x = (int) (Math.random() * drawPanel.getWidth());
                int y = (int) (Math.random() * drawPanel.getHeight());
                int radius = (int) (Math.random() * 50 + 10);

                Circle circle = new Circle(x, y, radius);
                drawPanel.add(circle);
                circles.add(circle);
            }

            // repaint the panel
            drawPanel.repaint();
        } else if (e.getSource() == randPairButton) {
            // get the number of pairs from the text field
            int numPairs = Integer.parseInt(numPairsField.getText());

            // generate random pairs and lines
            for (int i = 0; i < numPairs; i++) {
                int index1 = (int) (Math.random() * circles.size());
                int index2 = (int) (Math.random() * circles.size());

                Circle circle1 = circles.get(index1);
                Circle circle2 = circles.get(index2);

                Line line = new Line(circle1, circle2);
                drawPanel.add(line);
                lines.add(line);
            }

            // repaint the panel
            drawPanel.repaint();
        }
    }

    // a class to represent a circle
    private class Circle extends JComponent {

        private int x;
        private int y;
        private int radius;

        public Circle(int x, int y, int radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            setBounds(x - radius, y - radius, 2 * radius, 2 * radius);
        }

        // draw the circle
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillOval(0, 0, 2 * radius, 2 * radius);
        }
    }

    // a class to represent a line connecting two circles
    private class Line extends JComponent {

        private Circle circle1;
        private Circle circle2;

        public Line(Circle circle1, Circle circle2) {
            this.circle1 = circle1;
            this.circle2 = circle2;

            // calculate the bounds of the line
            int x1 = circle1.x + circle1.radius;
            int y1 = circle1.y + circle1.radius;
            int x2 = circle2.x + circle2.radius;
            int y2 = circle2.y + circle2.radius;
            int width = Math.abs(x2 - x1);
            int height = Math.abs(y2 - y1);
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);

            setBounds(x, y, width, height);
        }

        // draw the line
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            g.drawLine(circle1.x + circle1.radius, circle1.y + circle1.radius,
                    circle2.x + circle2.radius, circle2.y + circle2.radius);
        }
    }

    public static void main(String[] args) {
        new RandomCirclesAndLines();
    }
}
