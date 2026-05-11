/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jegor InfinitySoftware
 */
package com.infinitymath.geometria;

import com.infinitymath.geometria.ObjetoMatematico;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Clase que representa una recta
 * dentro del plano cartesiano.
 *
 * Hereda de ObjetoMatematico y define
 * cómo debe dibujarse gráficamente.
 *
 * La ecuación utilizada es:
 *
 * y = mx + b
 *
 * Donde:
 * - m = pendiente de la recta
 * - b = intersección con el eje Y
 */
public class Recta extends ObjetoMatematico {

    /**
     * Pendiente de la recta.
     *
     * Indica la inclinación:
     * - Positiva  -> ascendente
     * - Negativa  -> descendente
     * - Cero      -> horizontal
     */
    private double m;

    /**
     * Intersección con el eje Y.
     *
     * Valor donde la recta corta
     * el eje vertical.
     */
    private double b;

    /**
     * Constructor de la clase.
     *
     * @param m Pendiente de la recta.
     * @param b Intersección con el eje Y.
     */
    public Recta(double m, double b) {

        this.m = m;
        this.b = b;
    }

    /**
     * Dibuja la recta sobre el plano cartesiano.
     *
     * @param gc Contexto gráfico utilizado para dibujar.
     * @param escala Escala actual del plano.
     * @param centroX Coordenada X del centro del canvas.
     * @param centroY Coordenada Y del centro del canvas.
     */
    @Override
    public void dibujar(
            GraphicsContext gc,
            double escala,
            double centroX,
            double centroY
    ) {

        // Color de la recta
        gc.setStroke(Color.RED);

        /**
         * Se utilizan dos puntos extremos
         * para representar visualmente
         * la recta en pantalla.
         */

        // Punto inicial
        double x1 = -100;
        double y1 = (m * x1) + b;

        // Punto final
        double x2 = 100;
        double y2 = (m * x2) + b;

        // =========================
        // Conversión a pantalla
        // =========================

        double pantallaX1 =
                centroX + (x1 * escala);

        double pantallaY1 =
                centroY - (y1 * escala);

        double pantallaX2 =
                centroX + (x2 * escala);

        double pantallaY2 =
                centroY - (y2 * escala);

        // Dibujar línea
        gc.strokeLine(
                pantallaX1,
                pantallaY1,
                pantallaX2,
                pantallaY2
        );
    }

    // =========================
    // SETTERS
    // =========================

    /**
     * Modifica la pendiente de la recta.
     */
    public void setM(double m) {
        this.m = m;
    }

    /**
     * Modifica la intersección con el eje Y.
     */
    public void setB(double b) {
        this.b = b;
    }

    // =========================
    // GETTERS
    // =========================

    /**
     * Retorna la pendiente de la recta.
     */
    public double getM() {
        return m;
    }

    /**
     * Retorna la intersección con el eje Y.
     */
    public double getB() {
        return b;
    }
}