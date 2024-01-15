package espacial;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Expansion {

    
    
    // arreglo de rs: arreglo de 6 elementos, donde
    // los 3 primeros son los r1 de los canales (r,g,b),
    // los 3 segundos son los r2 de los canales (r,g,b)
    // R[0,3]
    // G[1,4]
    // B[2,5]
    public static Image expansionLinealColor(int rs[],Image imagen){
    
        BufferedImage aux = 
            herramientas.HerramientasImagen.toBufferedImage(imagen);
    
        for (int x=0; x< aux.getWidth();x++)
            for(int y=0; y< aux.getHeight();y++){
              // obtener el color
              Color pixel = new Color(aux.getRGB(x, y));
              int r = OperacionesBasicas.validar(
                      (pixel.getRed()-rs[0])*(255/(rs[3]-rs[0])));
              int g = OperacionesBasicas.validar(
                      (pixel.getGreen()-rs[1])*(255/(rs[4]-rs[1])));
              int b = OperacionesBasicas.validar(
                      (pixel.getBlue()-rs[2])*(255/(rs[5]-rs[2])));
            // validamos 
            pixel = new Color(r, g, b);
            aux.setRGB(x, y, pixel.getRGB());
            }
        
        return herramientas.HerramientasImagen.toImage(aux);
    }

    
}
