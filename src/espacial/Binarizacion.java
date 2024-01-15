package espacial;

import herramientas.HerramientasImagen;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class Binarizacion {

    public static Image binarizarImagen(Image original, int j){
        // to Buffer
            BufferedImage aux = herramientas.HerramientasImagen.toBufferedImage(original);
            
            for(int x=0;x<aux.getWidth();x++){
            for(int y=0;y<aux.getHeight();y++){
            
            //promedio de colores rgb:
            Color col = new Color(aux.getRGB(x, y));
            int base = col.getBlue();
            if (base>=j) base = 255;
            if (base<j) base = 0;
            col = new Color(base,base,base);
            aux.setRGB(x, y, col.getRGB());
            }}
            Image img = HerramientasImagen.toImage(aux);
            return img;
        
        }
    
    
}
