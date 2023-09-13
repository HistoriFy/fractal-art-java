import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FractalArtLauncher {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fractal Art Launcher");
        String[] fractalOptions = {"Select a Fractal Art Type", "Sierpinski Triangle", "Rational Map", "Phoenix Fractal", "Multibrot Set", "Mandelbrot"};
        JComboBox<String> fractalDropdown = new JComboBox<>(fractalOptions);

        fractalDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = fractalDropdown.getSelectedIndex();

                switch (choice) {
                    case 1:
                        SierpinskiTriangle.main(args);
                        break;
                    case 2:
                        RationalMap.main(args);
                        break;
                    case 3:
                        PhoenixFractal.main(args);
                        break;
                    case 4:
                        MultibrotSet.main(args);
                        break;
                    case 5:
                        Mandelbrot.main(args);
                        break;
                    default:
                        // Do nothing or display a message
                }
            }
        });

        frame.add(fractalDropdown);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
