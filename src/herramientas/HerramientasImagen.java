package herramientas;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HerramientasImagen {

    public static BufferedImage abrirImagenComoBufferedImage() {
        try {
            // Definir los filtros para lectura
            FileNameExtensionFilter filtro =
                    new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png", "bmp");
            // Crear un selector de archivos
            JFileChooser selector = new JFileChooser();
            // Agregar el filtro al selector
            selector.addChoosableFileFilter(filtro);
            // Especificar que solo se puedan abrir archivos
            selector.setFileSelectionMode(JFileChooser.FILES_ONLY);

            // Ejecutar el selector de imágenes
            int res = selector.showOpenDialog(null);

            if (res == JFileChooser.APPROVE_OPTION) {
                File archivo = selector.getSelectedFile();
                return ImageIO.read(archivo);
            }
        } catch (IOException ex) {
            Logger.getLogger(HerramientasImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Image toImage(BufferedImage bi) {
        return bi.getScaledInstance(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public static BufferedImage toBufferedImage(Image imagen) {
        BufferedImage bi =
                new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), BufferedImage.TYPE_INT_RGB);

        Graphics2D nueva = bi.createGraphics();
        nueva.drawImage(imagen, 0, 0, null);
        nueva.dispose();

        return bi;
    }

    public static Image copiarImagen(Image i) {
        BufferedImage bi = toBufferedImage(i);
        return bi.getScaledInstance(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
    }
}
