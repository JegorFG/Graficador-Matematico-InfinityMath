/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chuss
 */
package com.infinitymath.ui.funciones;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import com.infinitymath.math.Funcion;
import com.infinitymath.math.FuncionExpresion;
import com.infinitymath.util.ModoPlano;
import javafx.scene.control.Alert;
import com.infinitymath.render.PlanoCartesiano;

public class PanelFunciones extends BorderPane {

    private PlanoCartesiano plano;
    
    public PanelFunciones() {

        inicializar();
    }

    private void inicializar() {
        HBox controles = new HBox(10);

        controles.setPadding(new Insets(10));

        TextField txtFuncion = new TextField();

        txtFuncion.setPromptText("f(x)");

        Button btnGraficar = new Button("Graficar");

        controles.getChildren().addAll(
                new Label("Función:"),
                txtFuncion,
                btnGraficar);

        setTop(controles);
        
        plano = new PlanoCartesiano(800, 600);
        plano.setModoPlano(ModoPlano.FUNCIONES);
        plano.widthProperty().bind(widthProperty());
        plano.heightProperty().bind(heightProperty().subtract(60));
        
        setCenter(plano);
        
        btnGraficar.setOnAction(e -> {
            try {

                String expresion = txtFuncion.getText();

                Funcion funcion = new FuncionExpresion(expresion);

                plano.actualizarGrafica(funcion);

            } catch (Exception ex) {

                Alert alerta = new Alert(Alert.AlertType.ERROR);

                alerta.setTitle("Error");

                alerta.setHeaderText("Expresión inválida");

                alerta.setContentText("Verifica la función ingresada.");

                alerta.showAndWait();
            }
        });
    }
}