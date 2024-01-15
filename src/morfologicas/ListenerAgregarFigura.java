package morfologicas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import herramientas.HerramientasImagen;

public class ListenerAgregarFigura implements ActionListener {
    JFrameMorfo frame;
    public ListenerAgregarFigura(JFrameMorfo frame){
        this.frame = frame;

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        BufferedImage nuevo = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

        frame.actualizarLienzo(HerramientasImagen.toImage(nuevo));

    }

}
