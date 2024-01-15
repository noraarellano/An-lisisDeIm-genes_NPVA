package gui;
import javax.swing.*;
import herramientas.HerramientasImagen;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class LienzoControlado extends JFrame {
    private BufferedImage imagen;
    private int x = 0;
    private int y = 0;
    private double grados = 0;
    private double factorEscalado = 1.0; 

    private JSlider sliderTraslacionX;
    private JSlider sliderTraslacionY;
    private JSlider sliderRotacion;
    private JSlider sliderEscalado; 

    public LienzoControlado(BufferedImage imagen) {
        super("Lienzo Controlado");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.imagen = imagen;

        add(new Lienzo(imagen));
        agregarControles();

        setLocationRelativeTo(null);
    }

    private void agregarControles() {
        JPanel panelControles = new JPanel(new GridLayout(4, 2)); 

        sliderTraslacionX = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
        sliderTraslacionY = new JSlider(JSlider.VERTICAL, -100, 100, 0);
        sliderRotacion = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
        sliderEscalado = new JSlider(JSlider.HORIZONTAL, 10, 200, 100); 

        sliderTraslacionX.addChangeListener(e -> {
            x = sliderTraslacionX.getValue();
            repaint();
        });

        sliderTraslacionY.addChangeListener(e -> {
            y = sliderTraslacionY.getValue();
            repaint();
        });

        sliderRotacion.addChangeListener(e -> {
            grados = sliderRotacion.getValue();
            repaint();
        });

        sliderEscalado.addChangeListener(e -> {
            factorEscalado = sliderEscalado.getValue() / 100.0;
            repaint();
        });

        panelControles.add(new JLabel("Traslación X:"));
        panelControles.add(sliderTraslacionX);

        panelControles.add(new JLabel("Traslación Y:"));
        panelControles.add(sliderTraslacionY);

        panelControles.add(new JLabel("Rotación:"));
        panelControles.add(sliderRotacion);

        panelControles.add(new JLabel("Escalado (%):")); 
        panelControles.add(sliderEscalado);

        add(panelControles, BorderLayout.SOUTH);
    }

    class Lienzo extends JPanel {

        private BufferedImage imagen;

        public Lienzo(BufferedImage imagen) {
            this.imagen = imagen;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            AffineTransform transformacion = new AffineTransform();
            transformacion.translate(x, y);
            transformacion.rotate(Math.toRadians(grados), imagen.getWidth() / 2.0, imagen.getHeight() / 2.0);
            transformacion.scale(factorEscalado, factorEscalado);

            g2d.drawImage(imagen, transformacion, this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BufferedImage imagen = HerramientasImagen.abrirImagenComoBufferedImage();
            if (imagen != null) {
                LienzoControlado lienzo = new LienzoControlado(imagen);
                lienzo.setVisible(true);
            }
        });
    }
}
