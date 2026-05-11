/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jegor InfinitySoftware
 */
package com.infinitymath.geometria;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Clase que representa un círculo
 * dentro del plano cartesiano.
 *
 * Hereda de ObjetoMatematico y define
 * cómo debe dibujarse gráficamente.
 *
 * La ecuación general representada es:
 *
 * (x - h)² + (y - k)² = r²
 *
 * Donde:
 * - h = coordenada X del centro
 * - k = coordenada Y del centro
 * - r = radio del círculo
 */
public class Circulo extends ObjetoMatematico {

    /**
     * Coordenada X del centro del círculo.
     */
    private double h;

    /**
     * Coordenada Y del centro del círculo.
     */
    private double k;

    /**
     * Radio del círculo.
     */
    private double radio;

    /**
     * Constructor de la clase.
     *
     * @param h Coordenada X del centro.
     * @param k Coordenada Y del centro.
     * @param radio Radio del círculo.
     */
    public Circulo(double h, double k, double radio) {

        this.h = h;
        this.k = k;
        this.radio = radio;
    }

    /**
     * Dibuja el círculo sobre el plano cartesiano.
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

        // Color del círculo
        gc.setStroke(Color.BLUE);

        /**
         * Conversión de coordenadas matemáticas
         * a coordenadas de pantalla.
         *
         * Se resta el radio para obtener
         * la esquina superior izquierda
         * requerida por strokeOval().
         */
        double xPantalla =
                centroX + ((h - radio) * escala);

        double yPantalla =
                centroY - ((k + radio) * escala);

        // Diámetro visual del círculo
        double diametro =
                radio * 2 * escala;

        // Dibujar círculo
        gc.strokeOval(
                xPantalla,
                yPantalla,
                diametro,
                diametro
        );
    }

    // =========================
    // GETTERS
    // =========================

    /**
     * Retorna la coordenada X del centro.
     */
    public double getH() {
        return h;
    }

    /**
     * Retorna la coordenada Y del centro.
     */
    public double getK() {
        return k;
    }

    /**
     * Retorna el radio del círculo.
     */
    public double getRadio() {
        return radio;
    }

    // =========================
    // SETTERS
    // =========================

    /**
     * Modifica la coordenada X del centro.
     */
    public void setH(double h) {
        this.h = h;
    }

    /**
     * Modifica la coordenada Y del centro.
     */
    public void setK(double k) {
        this.k = k;
    }

    /**
     * Modifica el radio del círculo.
     */
    public void setRadio(double radio) {
        this.radio = radio;
    }
}