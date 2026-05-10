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

/**
 * Clase encargada de construir y mostrar
 * la ventana principal de la aplicación.
 *
 * Esta clase administra:
 *
 * - El plano de funciones matemáticas.
 * - El plano de geometría analítica.
 * - Los controles gráficos de interacción.
 * - Los eventos de los botones.
 * - La creación de la interfaz JavaFX.
 */
public class VentanaPrincipal {

    /**
     * Plano utilizado para graficar funciones.
     */
    private PlanoCartesiano planoFunciones;

    /**
     * Plano utilizado para geometría analítica.
     */
    private PlanoCartesiano planoGeometria;

    /**
     * Método encargado de construir y mostrar
     * toda la interfaz principal.
     *
     * @param stage Ventana principal de JavaFX.
     */
    public void mostrar(Stage stage) {

        // Contenedor principal
        BorderPane root = new BorderPane();

        // =========================
        // CREACIÓN DE PLANOS
        // =========================

        planoFunciones =
                new PlanoCartesiano(800, 600);

        planoGeometria =
                new PlanoCartesiano(800, 600);

        // Activar modo funciones
        planoFunciones.setModoFunciones(true);

        // Desactivar funciones para usar geometría
        planoGeometria.setModoFunciones(false);

        // =========================
        // OBJETOS GEOMÉTRICOS
        // =========================

        // Crear círculo inicial
        Circulo circulo =
                new Circulo(2, 1, 3);

        planoGeometria.agregarObjeto(circulo);

        // Crear recta inicial
        Recta recta =
                new Recta(1, 0);

        planoGeometria.agregarObjeto(recta);

        // =========================
        // CAMPOS DE TEXTO
        // =========================

        // Campo para función matemática
        TextField txtFuncion =
                new TextField("x^2");

        // Datos del círculo
        TextField txtH =
                new TextField("2");

        TextField txtK =
                new TextField("1");

        TextField txtRadio =
                new TextField("3");

        // Datos de la recta
        TextField txtM =
                new TextField("1");

        TextField txtB =
                new TextField("0");

        // =========================
        // BOTONES
        // =========================

        Button btnActualizar =
                new Button("Actualizar");

        Button btnGraficar =
                new Button("Graficar");

        // Ajustar tamaño del campo función
        txtFuncion.setPrefWidth(300);

        // =========================
        // CONTENEDORES
        // =========================

        HBox controles = new HBox(10);

        controles.setPadding(new Insets(10));

        // Sistema de pestañas
        TabPane tabPane = new TabPane();

        Tab tabFunciones =
                new Tab("Funciones");

        Tab tabGeometria =
                new Tab("Geometría");

        // Paneles principales
        VBox panelFunciones =
                new VBox(10);

        VBox panelGeometria =
                new VBox(10);

        // =========================
        // AJUSTE RESPONSIVO
        // =========================

        // El plano se adapta al tamaño del panel
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

        // =========================
        // CONTENIDO DE PESTAÑAS
        // =========================

        tabFunciones.setContent(panelFunciones);

        tabGeometria.setContent(panelGeometria);

        // Barras de controles
        HBox controlesFunciones =
                new HBox(10);

        HBox controlesGeometria =
                new HBox(10);

        // Agregar pestañas
        tabPane.getTabs().addAll(
                tabFunciones,
                tabGeometria
        );

        // Agregar elementos a panel funciones
        panelFunciones.getChildren().addAll(
                controlesFunciones,
                planoFunciones
        );

        // Agregar elementos a panel geometría
        panelGeometria.getChildren().addAll(
                controlesGeometria,
                planoGeometria
        );

        // Permitir que el plano crezca dinámicamente
        VBox.setVgrow(
                planoFunciones,
                Priority.ALWAYS
        );

        VBox.setVgrow(
                planoGeometria,
                Priority.ALWAYS
        );

        // =========================
        // CONTROLES FUNCIONES
        // =========================

        controlesFunciones.getChildren().addAll(

                new Label("f(x)="),

                txtFuncion,

                btnGraficar
        );

        // =========================
        // CONTROLES GEOMETRÍA
        // =========================

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

        // ==================================================
        // EVENTO BOTÓN ACTUALIZAR GEOMETRÍA
        // ==================================================

        btnActualizar.setOnAction(e -> {

            try {

                // Actualizar datos del círculo
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

                // Actualizar datos de la recta
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

                // Redibujar plano
                planoGeometria.refrescar();

            }

            // Manejo de errores
            catch (Exception ex) {

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

        // ==================================================
        // EVENTO BOTÓN GRAFICAR FUNCIÓN
        // ==================================================

        btnGraficar.setOnAction(e -> {

            try {

                // Obtener expresión ingresada
                String expresion =
                        txtFuncion.getText();

                // Crear función dinámica
                FuncionExpresion funcion =
                        new FuncionExpresion(expresion);

                // Actualizar gráfica
                planoFunciones.actualizarGrafica(funcion);

            }

            // Manejo de errores
            catch (Exception ex) {

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

        // =========================
        // ESTRUCTURA FINAL
        // =========================

        root.setTop(controles);

        root.setCenter(tabPane);

        // Crear escena principal
        Scene scene =
                new Scene(root, 1000, 700);

        // Configuración de ventana
        stage.setTitle(
                "Graficador Matemático (InfinityMath)"
        );

        stage.setScene(scene);

        // Mostrar ventana
        stage.show();
    }
}