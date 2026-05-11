/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jegor InfinitySoftware
 */
package com.infinitymath.ui;

import com.infinitymath.ui.funciones.PanelFunciones;
import com.infinitymath.ui.geometria.PanelGeometria;
import com.infinitymath.ui.menu.SideMenu;
import javafx.scene.layout.BorderPane;

public class MainWindow extends BorderPane {
    
    private PanelFunciones panelFunciones;

    private PanelGeometria panelGeometria;
    
    public MainWindow() {

        inicializar();
        
    }

    private void inicializar() {
        SideMenu sideMenu = new SideMenu();

        setLeft(sideMenu);
        
        panelFunciones = new PanelFunciones();

        setCenter(panelFunciones);
        
        panelGeometria = new PanelGeometria();
        
        setCenter(panelGeometria);
        
        setLeft(sideMenu);
        
        sideMenu
            .getBtnFunciones()
            .setOnAction(e -> {
                setCenter(panelFunciones);
            });

        sideMenu
            .getBtnGeometria()
            .setOnAction(e -> {
                setCenter(panelGeometria);
            });
    }
}