import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RationalMap {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int MAX_DEPTH = 256;
    public static final Complex C = new Complex(0.355, 0.355);

    public static void main(String[] args) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                Complex z = new Complex((x - WIDTH / 2.0) / (WIDTH / 4.0), (y - HEIGHT / 2.0) / (HEIGHT / 4.0));
                int iterations = fractal(z, MAX_DEPTH);
                int color = iterations * 255 / MAX_DEPTH;
                image.setRGB(x, y, new Color(color, color, color).getRGB());
            }
        }

        JFrame frame = new JFrame("Rational Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(new JLabel(new ImageIcon(image)));
        frame.setVisible(true);
    }

    public static int fractal(Complex z, int depth) {
        if (depth <= 0 || z.magnitude() > 2.0) {
            return depth;
        }

        Complex numerator = z;
        Complex denominator = z.multiply(z).add(C);

        return fractal(numerator.divide(denominator), depth - 1);
    }
}

class Complex {
    private double re;
    private double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex add(Complex other) {
        return new Complex(this.re + other.re, this.im + other.im);
    }

    public Complex multiply(Complex other) {
        return new Complex(this.re * other.re - this.im * other.im, this.re * other.im + this.im * other.re);
    }

    public Complex divide(Complex other) {
        double denominator = other.re * other.re + other.im * other.im;
        return new Complex((this.re * other.re + this.im * other.im) / denominator, (this.im * other.re - this.re * other.im) / denominator);
    }

    public double magnitude() {
        return Math.sqrt(this.re * this.re + this.im * this.im);
    }
}
