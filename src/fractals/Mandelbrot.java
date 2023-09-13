import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Mandelbrot {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int MAX_ITER = 1000;
    public static final double X_MIN = -2.0;
    public static final double X_MAX = 1.0;
    public static final double Y_MIN = -1.5;
    public static final double Y_MAX = 1.5;

    public static void main(String[] args) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        mandelbrot(image, 0, 0, WIDTH, HEIGHT);
        
        JFrame frame = new JFrame("Mandelbrot Set");
        ImageIcon icon = new ImageIcon(image);
        frame.add(new JLabel(icon));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void mandelbrot(BufferedImage image, int x0, int y0, int width, int height) {
        if (width <= 0 || height <= 0) {
            return;
        }

        if (width == 1 || height == 1) {
            for (int x = x0; x < x0 + width; x++) {
                for (int y = y0; y < y0 + height; y++) {
                    double zx = (x * (X_MAX - X_MIN)) / (WIDTH - 1) + X_MIN;
                    double zy = (y * (Y_MAX - Y_MIN)) / (HEIGHT - 1) + Y_MIN;
                    int iter = iterate(zx, zy);
                    int color = Color.HSBtoRGB(iter / 256f, 1, iter / (iter + 8f));
                    image.setRGB(x, y, color);
                }
            }
        } else {
            int halfWidth = width / 2;
            int halfHeight = height / 2;

            mandelbrot(image, x0, y0, halfWidth, halfHeight);
            mandelbrot(image, x0 + halfWidth, y0, width - halfWidth, halfHeight);
            mandelbrot(image, x0, y0 + halfHeight, halfWidth, height - halfHeight);
            mandelbrot(image, x0 + halfWidth, y0 + halfHeight, width - halfWidth, height - halfHeight);
        }
    }

    public static int iterate(double zx, double zy) {
        double cX = zx;
        double cY = zy;
        int iter = MAX_ITER;
        while (zx * zx + zy * zy < 4 && iter > 0) {
            double tmp = zx * zx - zy * zy + cX;
            zy = 2.0 * zx * zy + cY;
            zx = tmp;
            iter--;
        }
        return iter;
    }
}
