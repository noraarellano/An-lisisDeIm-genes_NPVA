package espacial;

import herramientas.HerramientasImagen;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class OperacionesBasicas {

    public static Image escalaDeGrises(Image original){
        // to Buffer
        BufferedImage aux = herramientas.HerramientasImagen.toBufferedImage(original);
        
        for(int x=0;x<aux.getWidth();x++){
        for(int y=0;y<aux.getHeight();y++){
        
        //promedio de colores rgb:
        
        int c = aux.getRGB(x, y);
        
        Color c2 = new Color(c);
            int prom;
            prom=(c2.getRed()+c2.getGreen()+c2.getBlue())/3;
            c2 = new Color(prom,prom,prom);
            aux.setRGB(x, y, c2.getRGB());
        }}
        Image img = HerramientasImagen.toImage(aux);
        return img;
    }
    
    public static Image modificarIluminacion(Image original, int m){
        // to Buffer
        BufferedImage aux = herramientas.HerramientasImagen.toBufferedImage(original);
        
        for(int x=0;x<aux.getWidth();x++){
        for(int y=0;y<aux.getHeight();y++){
        
        //promedio de colores rgb:
        
        int c = aux.getRGB(x, y);
        Color c2 = new Color(c);
               
        c2 = new Color(validar(c2.getRed()+m),
                validar(c2.getGreen()+m),
                validar(c2.getBlue()+m));
            aux.setRGB(x, y, c2.getRGB());
        }}
        Image img = HerramientasImagen.toImage(aux);
        return img;
    }

    public static int validar(int i) {
        if(i>255)return 255;
        if(i<0)return 0;
        return i;
    }

    
}
