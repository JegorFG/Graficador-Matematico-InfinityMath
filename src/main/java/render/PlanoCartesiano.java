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

/**
 * Clase encargada de representar y dibujar un plano cartesiano.
 *
 * Esta clase hereda de Canvas de JavaFX y permite:
 *
 * - Dibujar el plano cartesiano.
 * - Graficar funciones matemáticas.
 * - Detectar raíces y extremos.
 * - Dibujar objetos geométricos.
 * - Detectar intersecciones entre figuras.
 * - Aplicar zoom mediante la rueda del mouse.
 */
public class PlanoCartesiano extends Canvas {

    /**
     * Indica si el plano se encuentra en modo funciones.
     * Si es false, solamente se dibujan objetos matemáticos.
     */
    private boolean modoFunciones = true;

    /**
     * Contexto gráfico utilizado para dibujar sobre el Canvas.
     */
    private GraphicsContext gc;

    /**
     * Escala del plano cartesiano.
     * Controla el tamaño visual de las unidades.
     */
    private double escala = 40;

    /**
     * Función matemática actualmente graficada.
     */
    private Funcion funcionActual;

    /**
     * Lista de objetos matemáticos dibujados en el plano.
     */
    private List<ObjetoMatematico> objetos = new ArrayList<>();

    /**
     * Constructor del plano cartesiano.
     *
     * @param ancho Ancho del canvas.
     * @param alto Alto del canvas.
     */
    public PlanoCartesiano(double ancho, double alto) {

        // Inicializa el canvas con el tamaño recibido
        super(ancho, alto);

        // Obtiene el contexto gráfico
        gc = getGraphicsContext2D();

        // Dibuja el plano inicialmente
        dibujarPlano();

        // Función inicial por defecto
        Funcion funcion = new FuncionCuadratica(1, 0, 0);

        // Grafica la función inicial
        actualizarGrafica(funcion);

        /**
         * Evento de scroll para aplicar zoom.
         */
        this.setOnScroll(e -> {

            // Zoom in
            if (e.getDeltaY() > 0) {
                escala += 5;
            }

            // Zoom out
            else {
                escala -= 5;
            }

            // Evita valores negativos o demasiado pequeños
            if (escala < 5) {
                escala = 5;
            }

            // Redibuja todo el plano
            redibujar();
        });
    }

    /**
     * Dibuja el plano cartesiano completo.
     */
    private void dibujarPlano() {

        /**
         * Listener para redibujar automáticamente
         * cuando cambia el ancho.
         */
        widthProperty().addListener(e -> {
            redibujar();
        });

        /**
         * Listener para redibujar automáticamente
         * cuando cambia el alto.
         */
        heightProperty().addListener(e -> {
            redibujar();
        });

        double ancho = getWidth();
        double alto = getHeight();

        // Centro del plano
        double centroX = ancho / 2;
        double centroY = alto / 2;

        // =========================
        // Fondo
        // =========================
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, ancho, alto);

        // =========================
        // Cuadrícula
        // =========================
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

        // =========================
        // Ejes principales
        // =========================

        // Eje X
        gc.setStroke(Color.BLACK);
        gc.strokeLine(0, centroY, ancho, centroY);

        // Eje Y
        gc.strokeLine(centroX, 0, centroX, alto);

        // =========================
        // Numeración eje X
        // =========================
        gc.setFill(Color.BLACK);

        for (int i = -20; i <= 20; i++) {

            // Evita dibujar el cero
            if (i == 0) continue;

            double x = convertirX(i);

            gc.fillText(
                    String.valueOf(i),
                    x - 5,
                    centroY + 15
            );
        }

        // =========================
        // Numeración eje Y
        // =========================
        for (int i = -20; i <= 20; i++) {

            double y = convertirY(i);

            gc.fillText(
                    String.valueOf(i),
                    getWidth() / 2 + 5,
                    y - 2
            );
        }
    }

    /**
     * Grafica una función matemática.
     *
     * @param funcion Función a dibujar.
     */
    private void graficarFuncion(Funcion funcion) {

        gc.setStroke(Color.RED);

        // Precisión del dibujo
        double paso = 0.1;

        double xAnterior = -10;
        double yAnterior = funcion.evaluar(xAnterior);

        for (double x = -10; x <= 10; x += paso) {

            double y = funcion.evaluar(x);

            // Conversión a coordenadas de pantalla
            double pantallaX1 = convertirX(xAnterior);
            double pantallaY1 = convertirY(yAnterior);

            double pantallaX2 = convertirX(x);
            double pantallaY2 = convertirY(y);

            // Dibuja segmentos consecutivos
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

    /**
     * Detecta raíces aproximadas de una función.
     *
     * Una raíz ocurre cuando la función cambia de signo.
     *
     * @param funcion Función a analizar.
     */
    private void detectarRaices(Funcion funcion) {

        gc.setFill(Color.BLUE);

        double paso = 0.1;

        for (double x = -100; x < 100; x += paso) {

            double y1 = funcion.evaluar(x);
            double y2 = funcion.evaluar(x + paso);

            // Detecta cambio de signo
            if ((y1 < 0 && y2 > 0)
                    || (y1 > 0 && y2 < 0)) {

                double pantallaX = convertirX(x);
                double pantallaY = convertirY(0);

                // Punto raíz
                gc.fillOval(
                        pantallaX - 5,
                        pantallaY - 5,
                        10,
                        10
                );

                // Mostrar aproximación
                gc.fillText(
                        String.format("%.2f", x),
                        pantallaX + 5,
                        pantallaY - 5
                );
            }
        }
    }

    /**
     * Actualiza completamente la gráfica actual.
     *
     * @param funcion Nueva función a graficar.
     */
    public void actualizarGrafica(Funcion funcion) {

        this.funcionActual = funcion;

        dibujarPlano();

        graficarFuncion(funcion);

        detectarRaices(funcion);

        detectarExtremos(funcion);
    }

    /**
     * Redibuja completamente el plano.
     */
    private void redibujar() {

        dibujarPlano();

        // Si está en modo funciones
        if (modoFunciones && funcionActual != null) {

            graficarFuncion(funcionActual);

            detectarRaices(funcionActual);

            detectarExtremos(funcionActual);
        }

        // Dibuja objetos matemáticos
        for (ObjetoMatematico obj : objetos) {

            obj.dibujar(
                    gc,
                    escala,
                    getWidth() / 2,
                    getHeight() / 2
            );
        }

        /**
         * Detecta intersecciones entre círculos y rectas.
         */
        for (ObjetoMatematico o1 : objetos) {

            for (ObjetoMatematico o2 : objetos) {

                if (o1 instanceof Circulo c
                        && o2 instanceof Recta r) {

                    detectarInterseccionesExactas(c, r);
                }
            }
        }
    }

    // =========================
    // MÉTODOS DE CONVERSIÓN
    // =========================

    /**
     * Convierte coordenadas matemáticas X
     * a coordenadas de pantalla.
     */
    private double convertirX(double x) {

        double centroX = getWidth() / 2;

        return centroX + (x * escala);
    }

    /**
     * Convierte coordenadas matemáticas Y
     * a coordenadas de pantalla.
     */
    private double convertirY(double y) {

        double centroY = getHeight() / 2;

        return centroY - (y * escala);
    }

    /**
     * Detecta máximos y mínimos locales.
     *
     * @param funcion Función a analizar.
     */
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

            // Mínimo local
            if (yActual < yAnterior
                    && yActual < ySiguiente) {

                marcarExtremo(x, yActual, "Min");
            }

            // Máximo local
            if (yActual > yAnterior
                    && yActual > ySiguiente) {

                marcarExtremo(x, yActual, "Max");
            }
        }
    }

    /**
     * Dibuja un punto extremo.
     */
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

    /**
     * Agrega un objeto matemático al plano.
     */
    public void agregarObjeto(ObjetoMatematico objeto) {

        objetos.add(objeto);

        redibujar();
    }

    /**
     * Detecta intersecciones exactas
     * entre un círculo y una recta.
     */
    private void detectarInterseccionesExactas(Circulo circulo, Recta recta) {

        // Centro y radio del círculo
        double h = circulo.getH();
        double k = circulo.getK();
        double r = circulo.getRadio();

        // Pendiente y término independiente
        double m = recta.getM();
        double b = recta.getB();

        /**
         * Construcción de ecuación cuadrática
         * derivada de sustituir la recta
         * en la ecuación del círculo.
         */
        double A = 1 + Math.pow(m, 2);

        double B =
                (2 * m * (b - k))
                - (2 * h);

        double C =
                Math.pow(h, 2)
                + Math.pow(b - k, 2)
                - Math.pow(r, 2);

        // Discriminante
        double discriminante =
                Math.pow(B, 2)
                - (4 * A * C);

        gc.setFill(Color.PURPLE);

        String tipoRecta = "";

        String descripcion = "";

        // Tolerancia para errores decimales
        double epsilon = 0.0001;

        // =========================
        // RECTA SECANTE
        // =========================
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

        // =========================
        // RECTA TANGENTE
        // =========================
        else if (Math.abs(discriminante) <= epsilon) {

            double x =
                    -B / (2 * A);

            double y =
                    (m * x) + b;

            dibujarPuntoInterseccion(x, y);

            tipoRecta = "RECTA TANGENTE";

            descripcion = "Toca el círculo en 1 punto.";
        }

        // =========================
        // RECTA EXTERIOR
        // =========================
        else {

            tipoRecta = "RECTA EXTERIOR";

            descripcion = "No intersecta el círculo.";
        }

        // Mostrar información
        gc.setFill(Color.BLACK);

        gc.fillText(tipoRecta, 20, 20);

        gc.fillText(
                "Δ = "
                + String.format("%.2f", discriminante),
                20,
                40
        );

        gc.fillText(descripcion, 20, 60);
    }

    /**
     * Dibuja un punto de intersección.
     */
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

    /**
     * Fuerza el redibujado del plano.
     */
    public void refrescar() {
        redibujar();
    }

    /**
     * Activa o desactiva el modo funciones.
     */
    public void setModoFunciones(boolean modoFunciones) {
        this.modoFunciones = modoFunciones;
    }
}