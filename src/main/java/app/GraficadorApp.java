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

public class GraficadorApp extends Application {

    @Override
    public void start(Stage stage) {

        VentanaPrincipal ventana = new VentanaPrincipal();

        ventana.mostrar(stage);
    }
}