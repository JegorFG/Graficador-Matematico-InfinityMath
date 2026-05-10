/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jegor InfinitySoftware
 */
package app;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.VentanaPrincipal;

/**
 * Clase principal de la aplicación.
 *
 * Hereda de Application de JavaFX y representa
 * el punto de inicio de la interfaz gráfica.
 */
public class GraficadorApp extends Application {

    /**
     * Método que JavaFX ejecuta automáticamente
     * al iniciar la aplicación.
     *
     * @param stage Ventana principal proporcionada por JavaFX.
     */
    @Override
    public void start(Stage stage) {

        // Crear la ventana principal del sistema
        VentanaPrincipal ventana = new VentanaPrincipal();

        // Mostrar la interfaz en pantalla
        ventana.mostrar(stage);
    }
}