/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package render;

/**
 *
 * @author Jegor InfinitySoftware
 */

import geometria.Circulo;
import geometria.ObjetoMatematico;
import geometria.Recta;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import math.Funcion;
import math.FuncionCuadratica;

public class PlanoCartesiano extends Canvas {
    
    private boolean modoFunciones = true;

    private GraphicsContext gc;
    
    private double escala = 40;
    
    private Funcion funcionActual;
    
    private List<ObjetoMatematico> objetos = new ArrayList<>();

    public PlanoCartesiano(double ancho, double alto) {

        super(ancho, alto);

        gc = getGraphicsContext2D();

        dibujarPlano();

        Funcion funcion = new FuncionCuadratica(1, 0, 0);

        actualizarGrafica(funcion);
        
        this.setOnScroll(e -> {

            if (e.getDeltaY() > 0) {
                escala += 5;
            } else {
                escala -= 5;
            }

            // evitar escala negativa
            if (escala < 5) {
                escala = 5;
            }

            redibujar();
        });
    }

    private void dibujarPlano() {
        
        widthProperty().addListener(e -> {
            redibujar();
        });

        heightProperty().addListener(e -> {
            redibujar();
        });

        double ancho = getWidth();
        double alto = getHeight();

        double centroX = ancho / 2;
        double centroY = alto / 2;

        // Fondo
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, ancho, alto);

        // Cuadrícula
        gc.setStroke(Color.LIGHTGRAY);

        // Líneas verticales
        for (double x = centroX; x <= ancho; x += escala) {
            gc.strokeLine(x, 0, x, alto);
        }

        for (double x = centroX; x >= 0; x -= escala) {
            gc.strokeLine(x, 0, x, alto);
        }

        // Líneas horizontales
        for (double y = centroY; y <= alto; y += escala) {
            gc.strokeLine(0, y, ancho, y);
        }

        for (double y = centroY; y >= 0; y -= escala) {
            gc.strokeLine(0, y, ancho, y);
        }

        // Eje X
        gc.setStroke(Color.BLACK);
        gc.strokeLine(0, centroY, ancho, centroY);

        // Eje Y
        gc.strokeLine(centroX, 0, centroX, alto);
        
        // Numeración eje X
        gc.setFill(Color.BLACK);
        for (int i = -20; i <= 20; i++) {

            if (i == 0) continue;

            double x = convertirX(i);

            gc.fillText(
                    String.valueOf(i),
                    x - 5,
                    centroY + 15
            );
        }

        // Numeración eje Y
        for (int i = -20; i <= 20; i++) {

            double y = convertirY(i);

            gc.fillText(
                    String.valueOf(i),
                    getWidth() / 2 + 5,
                    y - 2
            );
        }
    }
    
    private void graficarFuncion(Funcion funcion) {

        gc.setStroke(Color.RED);

        double paso = 0.1;

        double xAnterior = -10;
        double yAnterior = funcion.evaluar(xAnterior);

        for (double x = -10; x <= 10; x += paso) {

            double y = funcion.evaluar(x);

            double pantallaX1 = convertirX(xAnterior);
            double pantallaY1 = convertirY(yAnterior);

            double pantallaX2 = convertirX(x);
            double pantallaY2 = convertirY(y);

            gc.strokeLine(
                    pantallaX1,
                    pantallaY1,
                    pantallaX2,
                    pantallaY2
            );

            xAnterior = x;
            yAnterior = y;
        }
    }
    
    private void detectarRaices(Funcion funcion) {

        gc.setFill(Color.BLUE);

        double paso = 0.1;

        for (double x = -100; x < 100; x += paso) {

            double y1 = funcion.evaluar(x);

            double y2 = funcion.evaluar(x + paso);

            // cambio de signo
            if ((y1 < 0 && y2 > 0)
                    || (y1 > 0 && y2 < 0)) {

                double pantallaX = convertirX(x);

                double pantallaY = convertirY(0);

                // dibujar punto
                gc.fillOval(
                        pantallaX - 5,
                        pantallaY - 5,
                        10,
                        10
                );

                // mostrar valor aproximado
                gc.fillText(
                        String.format("%.2f", x),
                        pantallaX + 5,
                        pantallaY - 5
                );
            }
        }
    }
    
    public void actualizarGrafica(Funcion funcion) {

        this.funcionActual = funcion;

        dibujarPlano();

        graficarFuncion(funcion);
        
        detectarRaices(funcion);
        
        detectarExtremos(funcion);
    }
    
    private void redibujar() {

        dibujarPlano();

        if (modoFunciones && funcionActual != null) {

            graficarFuncion(funcionActual);

            detectarRaices(funcionActual);

            detectarExtremos(funcionActual);
        }

        // dibujar objetos matemáticos
        for (ObjetoMatematico obj : objetos) {

            obj.dibujar(
                    gc,
                    escala,
                    getWidth() / 2,
                    getHeight() / 2
            );
        }
        
        for (ObjetoMatematico o1 : objetos) {

            for (ObjetoMatematico o2 : objetos) {

                if (o1 instanceof Circulo c
                        && o2 instanceof Recta r) {

                    detectarInterseccionesExactas(c, r);
                }
            }
        }
    }

    // MÉTODOS DE CONVERSIÓN
    private double convertirX(double x) {
        double centroX = getWidth() / 2;

        return centroX + (x * escala);
    }

    private double convertirY(double y) {
        double centroY = getHeight() / 2;

        return centroY - (y * escala);
    }
    
    private void detectarExtremos(Funcion funcion) {

        gc.setFill(Color.GREEN);

        double paso = 0.1;

        for (double x = -100; x < 100; x += paso) {

            double yAnterior =
                    funcion.evaluar(x - paso);

            double yActual =
                    funcion.evaluar(x);

            double ySiguiente =
                    funcion.evaluar(x + paso);

            // mínimo
            if (yActual < yAnterior
                    && yActual < ySiguiente) {

                marcarExtremo(x, yActual, "Min");
            }

            // máximo
            if (yActual > yAnterior
                    && yActual > ySiguiente) {

                marcarExtremo(x, yActual, "Max");
            }
        }
    }
    
    private void marcarExtremo(double x, double y, String tipo) {

        double pantallaX = convertirX(x);

        double pantallaY = convertirY(y);

        gc.fillOval(
                pantallaX - 5,
                pantallaY - 5,
                10,
                10
        );

        gc.fillText(
                tipo + ": ("
                + String.format("%.2f", x)
                + ", "
                + String.format("%.2f", y)
                + ")",
                pantallaX + 10,
                pantallaY - 10
        );
    }
    
    public void agregarObjeto(ObjetoMatematico objeto) {

        objetos.add(objeto);

        redibujar();
    }
    
    private void detectarInterseccionesExactas(Circulo circulo, Recta recta) {

        double h = circulo.getH();
        double k = circulo.getK();
        double r = circulo.getRadio();

        double m = recta.getM();
        double b = recta.getB();

        // coeficientes cuadrática
        double A =
                1 + Math.pow(m, 2);

        double B =
                (2 * m * (b - k))
                - (2 * h);

        double C =
                Math.pow(h, 2)
                + Math.pow(b - k, 2)
                - Math.pow(r, 2);

        double discriminante =
                Math.pow(B, 2)
                - (4 * A * C);

        gc.setFill(Color.PURPLE);
        
        String tipoRecta = "";

        String descripcion = "";
        
        double epsilon = 0.0001;

        // SECANTE
        if (discriminante > epsilon) {

            double x1 =
                    (-B + Math.sqrt(discriminante))
                    / (2 * A);

            double x2 =
                    (-B - Math.sqrt(discriminante))
                    / (2 * A);

            double y1 =
                    (m * x1) + b;

            double y2 =
                    (m * x2) + b;

            dibujarPuntoInterseccion(x1, y1);

            dibujarPuntoInterseccion(x2, y2);

            tipoRecta = "RECTA SECANTE";

            descripcion = "Interseca el círculo en 2 puntos.";

        }

        // TANGENTE
        else if (Math.abs(discriminante) <= epsilon) {

            double x =
                    -B / (2 * A);

            double y =
                    (m * x) + b;

            dibujarPuntoInterseccion(x, y);

            tipoRecta = "RECTA TANGENTE";

            descripcion = "Toca el círculo en 1 punto.";
        }

        // EXTERIOR
        else {

            tipoRecta = "RECTA EXTERIOR";

            descripcion = "No intersecta el círculo.";
        }
        
        gc.setFill(Color.BLACK);

        gc.fillText(tipoRecta, 20, 20);

        gc.fillText( "Δ = " + String.format("%.2f", discriminante), 20, 40);

        gc.fillText(descripcion, 20, 60);
    }
    
    private void dibujarPuntoInterseccion(double x, double y) {

        double pantallaX =
                convertirX(x);

        double pantallaY =
                convertirY(y);

        gc.fillOval(
                pantallaX - 5,
                pantallaY - 5,
                10,
                10
        );
    }
    
    /*private void detectarIntersecciones(Circulo circulo, Recta recta) {

        gc.setFill(Color.PURPLE);

        double paso = 0.1;

        for (double x = -100; x <= 100; x += paso) {

            double yRecta =
                    (recta.getM() * x)
                    + recta.getB();

            double distancia =
                    Math.pow(x - circulo.getH(), 2)
                    + Math.pow(yRecta - circulo.getK(), 2);

            double radio2 =
                    Math.pow(circulo.getRadio(), 2);

            // aproximación
            if (Math.abs(distancia - radio2) < 0.5) {

                double pantallaX =
                        convertirX(x);

                double pantallaY =
                        convertirY(yRecta);

                gc.fillOval(
                        pantallaX - 5,
                        pantallaY - 5,
                        10,
                        10
                );
            }
        }
    }*/
    
    public void refrescar() {
        redibujar();
    }

    public void setModoFunciones(boolean modoFunciones) {
        this.modoFunciones = modoFunciones;
    }
}
