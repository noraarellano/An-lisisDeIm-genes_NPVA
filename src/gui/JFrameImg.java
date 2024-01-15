package gui;


import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class JFrameImg extends JFrame {
    private JLabel lbImagen;

    public JFrameImg(Image imagen){
        inicializarComponentes(imagen);
    }

    private void inicializarComponentes(Image imagen) {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.lbImagen = new JLabel();
        add(this.lbImagen);
        this.lbImagen.setIcon(new ImageIcon(imagen));
        setSize(imagen.getWidth(this),
                imagen.getHeight(this));
        setVisible(true);
    }
    

}
