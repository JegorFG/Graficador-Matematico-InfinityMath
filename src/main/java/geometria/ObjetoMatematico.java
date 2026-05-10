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

/**
 * Clase abstracta que representa cualquier
 * objeto matemático dibujable dentro
 * del plano cartesiano.
 *
 * Esta clase sirve como base para figuras
 * geométricas u objetos matemáticos como:
 *
 * - Rectas
 * - Círculos
 * - Parábolas
 * - Segmentos
 * - Polígonos
 *
 * Obliga a las clases hijas a implementar
 * el método dibujar().
 */
public abstract class ObjetoMatematico {

    /**
     * Método abstracto encargado de dibujar
     * el objeto matemático en el plano.
     *
     * Cada clase hija define su propia lógica
     * de representación gráfica.
     *
     * @param gc Contexto gráfico utilizado para dibujar.
     * @param escala Escala actual del plano cartesiano.
     * @param centroX Coordenada X del centro del plano.
     * @param centroY Coordenada Y del centro del plano.
     */
    public abstract void dibujar(
            GraphicsContext gc,
            double escala,
            double centroX,
            double centroY
    );
}