package morfologicas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import herramientas.HerramientasImagen;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class JFrameMorfo extends JFrame{

    JPanel lienzo;
    JLabel etiqueta;
    JButton btnAgregarFigura;
    JFrameMorfo(Image imagenOriginal){
    // agregar directmante al canvas
    inicializarCanvas();

    }

    private void inicializarCanvas() {
        setSize(800, 600);
        setLayout(new BorderLayout());
        // en la parte central un panel
        this.lienzo = new JPanel();
        // jLabel
        this.etiqueta = new JLabel();
        this.etiqueta.setText("");
        // agregar el buffer
        BufferedImage bi = new BufferedImage(800, 500,
         BufferedImage.TYPE_INT_RGB);
         
        this.etiqueta.setIcon(new ImageIcon(HerramientasImagen.toImage(bi))); 
        lienzo.add(etiqueta);

        JPanel controles = new JPanel();
        controles.setLayout(new GridLayout(1,3));
        this.btnAgregarFigura = new JButton("Agregar Figura");
        this.btnAgregarFigura.
        addActionListener(new ListenerAgregarFigura(this));
        controles.add(this.btnAgregarFigura);

        add(lienzo, BorderLayout.CENTER);
        add(controles,BorderLayout.SOUTH);
        setVisible(true);

        //setDefaultCloseOperation(ABORT);
    }

    public void actualizarLienzo(Image imagenNueva){
        this.lienzo.repaint();
        this.etiqueta = new JLabel("");
        this.lienzo.removeAll();
        this.etiqueta.setIcon(new ImageIcon(imagenNueva));

        this.lienzo.add(this.etiqueta);
        
    }
    
}
