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

public class Recta extends ObjetoMatematico {

    private double m;
    private double b;

    public Recta(double m, double b) {

        this.m = m;
        this.b = b;
    }

    @Override
    public void dibujar(
            GraphicsContext gc,
            double escala,
            double centroX,
            double centroY
    ) {

        gc.setStroke(Color.RED);

        double x1 = -100;
        double y1 = (m * x1) + b;

        double x2 = 100;
        double y2 = (m * x2) + b;

        double pantallaX1 =
                centroX + (x1 * escala);

        double pantallaY1 =
                centroY - (y1 * escala);

        double pantallaX2 =
                centroX + (x2 * escala);

        double pantallaY2 =
                centroY - (y2 * escala);

        gc.strokeLine(
                pantallaX1,
                pantallaY1,
                pantallaX2,
                pantallaY2
        );
    }

    public void setM(double m) {
        this.m = m;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getM() {
        return m;
    }

    public double getB() {
        return b;
    }
}
