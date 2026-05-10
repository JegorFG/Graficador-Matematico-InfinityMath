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

public abstract class ObjetoMatematico {

    public abstract void dibujar(
            GraphicsContext gc,
            double escala,
            double centroX,
            double centroY
    );
}