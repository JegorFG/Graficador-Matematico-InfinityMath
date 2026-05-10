/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jegor InfinitySoftware
 */
package geometria;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circulo extends ObjetoMatematico {

    private double h;
    private double k;
    private double radio;

    public Circulo(double h, double k, double radio) {

        this.h = h;
        this.k = k;
        this.radio = radio;
    }

    @Override
    public void dibujar(
            GraphicsContext gc,
            double escala,
            double centroX,
            double centroY
    ) {

        gc.setStroke(Color.BLUE);

        double xPantalla =
                centroX + ((h - radio) * escala);

        double yPantalla =
                centroY - ((k + radio) * escala);

        double diametro =
                radio * 2 * escala;

        gc.strokeOval(
                xPantalla,
                yPantalla,
                diametro,
                diametro
        );
    }

    public double getH() {
        return h;
    }

    public double getK() {
        return k;
    }

    public double getRadio() {
        return radio;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setK(double k) {
        this.k = k;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }
        
}
