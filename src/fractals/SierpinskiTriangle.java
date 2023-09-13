import javax.swing.*;
import java.awt.*;

public class SierpinskiTriangle extends JPanel {

    private static final int WINDOW_SIZE = 800;

    public SierpinskiTriangle() {
        setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSierpinskiTriangle(g, WINDOW_SIZE / 2, WINDOW_SIZE / 2, WINDOW_SIZE / 2);
    }

    public void drawSierpinskiTriangle(Graphics g, int x, int y, int size) {
        if (size < 20) {
            return;
        }

        // Calculate the coordinates of the vertices of the triangle
        int[] xPoints = {x - size, x + size, x};
        int[] yPoints = {y + size, y + size, y - size};

        // Draw the triangle
        g.drawPolygon(xPoints, yPoints, 3);

        // Recursively draw smaller triangles at each vertex
        drawSierpinskiTriangle(g, x - size / 2, y + size / 2, size / 2);
        drawSierpinskiTriangle(g, x + size / 2, y + size / 2, size / 2);
        drawSierpinskiTriangle(g, x, y - size / 2, size / 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sierpinski Triangle");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new SierpinskiTriangle());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
