package frecuencias;

import java.awt.Image;
import java.awt.image.BufferedImage;

import espacial.OperacionesBasicas;
import gui.JFrameImg;
import gui.LienzoControlado;
import herramientas.HerramientasImagen;

public class FrecuenciasMain {
    public static void main(String[] args) {
        // obtenemos la imagen original
        Image imagenOriginal = HerramientasImagen.abrirImagen();

        // cponvertimos a grises
        Image grises = OperacionesBasicas.escalaDeGrises(imagenOriginal);
        // mostramos la imagen original
        JFrameImg frame1 = new JFrameImg(grises);
        

        Image imagenRuido = espacial.Ruido.agregarRuidoAditivo(grises, 5);

        JFrameImg frameRuido = new JFrameImg(imagenRuido);



        // Gestor para el calculo del espectro de las frecuencias
        GestorGrises gestor = new GestorGrises();
        GestorGrises gestor2 = new GestorGrises();


        BufferedImage bIGrises = HerramientasImagen.toBufferedImage(grises);
        BufferedImage bImageRuido = HerramientasImagen.toBufferedImage(imagenRuido);

        // convertimos a matriz de numeros complejos
        NumeroComplejo[][] aux = gestor.obtenerDatos(bImageRuido);
        NumeroComplejo[][] aux2 = gestor2.obtenerDatos(bIGrises);

        BufferedImage biFrecuenciasRuido = gestor.obtenerImagenFrecuencias(aux,
                bImageRuido.getWidth(),
                bImageRuido.getHeight(), true);

         BufferedImage biFrecuenciasGrises = gestor2.obtenerImagenFrecuencias(aux2,
                bImageRuido.getWidth(),
                bImageRuido.getHeight(), true);

        // mostramos la imagen original
        JFrameImg frame2 = new JFrameImg(HerramientasImagen.toImage(biFrecuenciasRuido));
        JFrameImg frame3 = new JFrameImg(HerramientasImagen.toImage(biFrecuenciasGrises));

        // aplicamos el filtro
        gestor.aplicarFiltroDeClase();

        BufferedImage resultante = gestor.obtenerImagenEspacial();

        JFrameImg frameResultado = new JFrameImg(HerramientasImagen.toImage(resultante));



    }
}
