package frecuencias;

import java.awt.image.BufferedImage;

import espacial.OperacionesBasicas;

public class GestorGrises {

    NumeroComplejo[][] transformada;

    // constructor por defecto
    public GestorGrises() {

    }

    public NumeroComplejo[][] obtenerDatos(BufferedImage imagenOriginal) {
        NumeroComplejo[][] aux = new NumeroComplejo[imagenOriginal.getWidth()][imagenOriginal.getHeight()];
        // obtenemos los datos por canal
        for (int y = 0; y < imagenOriginal.getHeight(); y++) {
            for (int x = 0; x < imagenOriginal.getWidth(); x++) {
                aux[x][y] = new NumeroComplejo(HerramientasColor.obtenerValorGris(imagenOriginal.getRGB(x, y)), 0);
            }
        }
        return aux;
    }

    public BufferedImage obtenerImagenFrecuencias(NumeroComplejo[][] datosIO, int w, int h,
            boolean reAjustarCuadrante) {
        /// obtenemos las dimensiones
        int anchoImagen = w;
        int altoImagen = h;
        BufferedImage aux = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_ARGB);

        FFTCalculo fft = new FFTCalculo();
        // construir el mapeo de representacion en frecuencias utilizando FFT

        this.transformada = fft.calculateFT(datosIO, false);

        // crear la imagen del espectro
        for (int y = 0; y < aux.getHeight(); y++) {
            for (int x = 0; x < aux.getWidth(); x++) {
                // modificamos la posicion de los cuadrantes
                int ejeX = reAjustarCuadrante ? (x + (anchoImagen / 2)) % anchoImagen : x;
                int ejeY = reAjustarCuadrante ? (y + (altoImagen / 2)) % altoImagen : y;
                // setear la info a la imagen
                // el que se ecuentre en la imagen
                int color1 = aux.getRGB(x, y);
                int color2 = obtenerColorRealDeFrecuenciaGrises(ejeX, ejeY, transformada);
                // int rgb = HerramientasColor.acumularColor(color1, color2);
                aux.setRGB(x, y, color2);

            }
        }

        return aux;
    }

    public BufferedImage obtenerImagenEspacial() {
        /// obtenemos las dimensiones
        int anchoImagen = this.transformada.length;
        int altoImagen = this.transformada.length;
        BufferedImage aux = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_ARGB);

        FFTCalculo fft = new FFTCalculo();
        // construir el mapeo de representacion en frecuencias utilizando FFT

        NumeroComplejo[][] transformadaInv = fft.calculateFT(this.transformada, true);

        // crear la imagen del espectro
        for (int y = 0; y < aux.getHeight(); y++) {
            for (int x = 0; x < aux.getWidth(); x++) {

                int color = (int) Math.abs(transformadaInv[x][y].getParteReal());
                color = OperacionesBasicas.validar(color);
                color = HerramientasColor.obtenerRGBdeGris(color);

                int rgb = HerramientasColor.acumularColor(aux.getRGB(x, y), color);
                aux.setRGB(x, y, rgb);
            }
        }

        return aux;

    }

    // proceso iterativo donde aplicamos el filtro ""
    public void aplicarFiltroDeClase() {
        NumeroComplejo[][] aux = new NumeroComplejo[256][256];
        for (int j = 0; j < 256; j++) {
            for (int b = 0; b < 256; b++) {

                aux[j][b] = new NumeroComplejo(0, 0);
            }
        }

        for (int x = 47; x <= 208; x++)
            for (int y = 47; y <= 208; y++) {
                aux[x][y] = new NumeroComplejo(transformada[x][y]);

            }

        // sustituir con aux
        this.transformada = aux;

    }

    private int obtenerColorRealDeFrecuenciaGrises(int ejeX, int ejeY, NumeroComplejo[][] transformada) {
        int color = (int) Math.abs(transformada[ejeX][ejeY].getParteReal());
        color = OperacionesBasicas.validar(color);
        color = HerramientasColor.obtenerRGBdeGris(color);
        return color;

    }

}
