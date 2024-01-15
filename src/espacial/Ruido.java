package espacial;

import java.util.Random;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;



public class Ruido {
    
    public static Image agregarRuidoAditivo(Image orginal, double por){
        BufferedImage bi = herramientas.HerramientasImagen.toBufferedImage(orginal);
        // double aux = por/100;
        int cp = (int)((por/100)*(bi.getHeight()*bi.getWidth()));
        Color aditivo = new Color(255,255,255);
        Random ran = new Random();
        int x,y;
        for(int j=0; j<cp;j++){
            // posicion aleatoria dentro de la imagen
            x = ran.nextInt(bi.getWidth());
            y = ran.nextInt(bi.getHeight());
            bi.setRGB(x, y, aditivo.getRGB());
        }
        
        return herramientas.HerramientasImagen.toImage(bi);
    }

}
