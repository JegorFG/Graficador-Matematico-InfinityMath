/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jegor InfinitySoftware
 */
package com.infinitymath.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.infinitymath.ui.MainWindow;

public class InfinityMathApp extends Application {

    @Override
    public void start(Stage stage) {

        MainWindow mainWindow = new MainWindow();

        Scene scene = new Scene(
                        mainWindow,
                        1200,
                        700);

        stage.setTitle("InfinityMath");

        stage.setScene(scene);

        stage.show();
    }
}