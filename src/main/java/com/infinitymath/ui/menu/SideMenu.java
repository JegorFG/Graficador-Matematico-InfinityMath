/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jegor InfinitySoftware
 */
package com.infinitymath.ui.menu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SideMenu extends VBox {
    
    private Button btnFunciones;
    
    private Button btnGeometria;

    public SideMenu() {

        setSpacing(10);

        setPadding(new Insets(10));

        btnFunciones = new Button("Funciones");

        btnGeometria = new Button("Geometría");

        getChildren().addAll(
                btnFunciones,
                btnGeometria);
    }

    public Button getBtnFunciones() {
        return btnFunciones;
    }

    public Button getBtnGeometria() {
        return btnGeometria;
    }
}