import javax.swing.*;
import java.awt.*;

public class PhoenixFractal extends JPanel {

    private final int MAX_DEPTH = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPhoenixFractal(g, 300, 300, 300, 100, 0, MAX_DEPTH);
    }

    private void drawPhoenixFractal(Graphics g, int x1, int y1, int x2, int y2, int depth, int maxDepth) {
        if (depth >= maxDepth) {
            return;
        }

        int midX = (x1 + x2) / 2;
        int midY = (y1 + y2) / 2;

        int diffX = x2 - x1;
        int diffY = y2 - y1;

        int newX = midX - diffY / 2;
        int newY = midY + diffX / 2;

        g.drawLine(x1, y1, newX, newY);
        g.drawLine(newX, newY, x2, y2);

        drawPhoenixFractal(g, x1, y1, newX, newY, depth + 1, maxDepth);
        drawPhoenixFractal(g, newX, newY, x2, y2, depth + 1, maxDepth);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Phoenix Fractal");
        PhoenixFractal phoenixFractal = new PhoenixFractal();
        frame.add(phoenixFractal);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
