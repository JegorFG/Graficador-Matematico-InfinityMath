/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jegor InfinitySoftware
 */
package ui;

import geometria.Circulo;
import geometria.Recta;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import math.FuncionExpresion;
import render.PlanoCartesiano;

public class VentanaPrincipal {

    private PlanoCartesiano planoFunciones;

    private PlanoCartesiano planoGeometria;
    
    public void mostrar(Stage stage) {
        
        BorderPane root = new BorderPane();

        planoFunciones = new PlanoCartesiano(800, 600);
        
        planoGeometria = new PlanoCartesiano(800, 600);
        
        planoFunciones.setModoFunciones(true);

        planoGeometria.setModoFunciones(false);
        
        Circulo circulo = new Circulo(2, 1, 3);

        planoGeometria.agregarObjeto(circulo);
        
        Recta recta = new Recta(1, 0);

        planoGeometria.agregarObjeto(recta);

        // Campos
        TextField txtFuncion = new TextField("x^2");
        
        TextField txtH = new TextField("2");

        TextField txtK = new TextField("1");

        TextField txtRadio = new TextField("3");

        TextField txtM = new TextField("1");

        TextField txtB = new TextField("0");
        
        Button btnActualizar = new Button("Actualizar");

        txtFuncion.setPrefWidth(300);
        
        Button btnGraficar = new Button("Graficar");

        HBox controles = new HBox(10);

        controles.setPadding(new Insets(10));
        
        TabPane tabPane = new TabPane();
        
        Tab tabFunciones = new Tab("Funciones");
        
        Tab tabGeometria = new Tab("Geometría");
        
        VBox panelFunciones = new VBox(10);
        
        VBox panelGeometria = new VBox(10);
        
        planoFunciones.widthProperty().bind(
                panelFunciones.widthProperty());

        planoFunciones.heightProperty().bind(
                panelFunciones.heightProperty()
                        .subtract(50));

        planoGeometria.widthProperty().bind(
                panelGeometria.widthProperty());

        planoGeometria.heightProperty().bind(
                panelGeometria.heightProperty()
                        .subtract(50));
        
        tabFunciones.setContent(panelFunciones);

        tabGeometria.setContent(panelGeometria);
        
        HBox controlesFunciones = new HBox(10);
        
        HBox controlesGeometria = new HBox(10);
        
        tabPane.getTabs().addAll(
                tabFunciones,
                tabGeometria);

        panelFunciones.getChildren().addAll(
                controlesFunciones, 
                planoFunciones);
        
        panelGeometria.getChildren().addAll(
                controlesGeometria,
                planoGeometria);
        
        VBox.setVgrow(
        planoFunciones,
        Priority.ALWAYS);
        
        VBox.setVgrow(
        planoGeometria,
        Priority.ALWAYS);
        
        controlesFunciones.getChildren().addAll(
                new Label("f(x)="),
                txtFuncion,
                btnGraficar);
        
        controlesGeometria.getChildren().addAll(

                new Label("H"),
                txtH,

                new Label("K"),
                txtK,

                new Label("Radio"),
                txtRadio,

                new Label("M"),
                txtM,

                new Label("B"),
                txtB,

                btnActualizar
        );
        
        // Evento botón
        btnActualizar.setOnAction(e -> {

            try {

                circulo.setH(
                        Double.parseDouble(
                                txtH.getText()
                        )
                );

                circulo.setK(
                        Double.parseDouble(
                                txtK.getText()
                        )
                );

                circulo.setRadio(
                        Double.parseDouble(
                                txtRadio.getText()
                        )
                );

                recta.setM(
                        Double.parseDouble(
                                txtM.getText()
                        )
                );

                recta.setB(
                        Double.parseDouble(
                                txtB.getText()
                        )
                );

                planoGeometria.refrescar();

            } catch (Exception ex) {

                Alert alerta =
                        new Alert(Alert.AlertType.ERROR);

                alerta.setTitle("Error");

                alerta.setHeaderText(
                        "Datos inválidos"
                );

                alerta.setContentText(
                        "Verifica los valores ingresados."
                );

                alerta.showAndWait();
            }
        });
        // Evento botón
        btnGraficar.setOnAction(e -> {

            try {

                String expresion =
                        txtFuncion.getText();

                FuncionExpresion funcion = new FuncionExpresion(expresion);

                planoFunciones.actualizarGrafica(funcion);

            } catch (Exception ex) {

                Alert alerta =
                        new Alert(Alert.AlertType.ERROR);

                alerta.setTitle("Error");

                alerta.setHeaderText(
                        "Expresión inválida"
                );

                alerta.setContentText(
                        "Verifica la función ingresada."
                );

                alerta.showAndWait();
            }
        });

        root.setTop(controles);

        root.setCenter(tabPane);

        Scene scene = new Scene(root, 1000, 700);

        stage.setTitle("Graficador Matemático (InfinityMath)");

        stage.setScene(scene);

        stage.show();
    }
}