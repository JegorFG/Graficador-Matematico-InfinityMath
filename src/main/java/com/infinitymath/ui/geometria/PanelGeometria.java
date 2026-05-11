/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jegor InfinitySoftware
 */
package com.infinitymath.ui.geometria;

import com.infinitymath.util.InformacionMatematica;
import com.infinitymath.util.ModoPlano;
import com.infinitymath.geometria.Circulo;
import com.infinitymath.geometria.Recta;
import java.util.List;
import java.util.Set;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import com.infinitymath.render.PlanoCartesiano;

public class PanelGeometria extends BorderPane {
    
    private PlanoCartesiano plano;
    
    private TextArea txtInfo;
    
    public PanelGeometria() {

        inicializar();
    }

    private void inicializar() {
        
        plano = new PlanoCartesiano(800, 600);

        plano.setModoPlano(ModoPlano.GEOMETRIA);

        //plano.widthProperty().bind(widthProperty());
        //plano.heightProperty().bind(heightProperty().subtract(60));

        setCenter(plano);
        
        VBox panelControles = new VBox(10);

        panelControles.setPadding(new Insets(10));
        
        TextField txtH = new TextField("0");

        TextField txtK = new TextField("0");

        TextField txtRadio = new TextField("5");

        Button btnCirculo = new Button("Agregar círculo");
        
        TextField txtM = new TextField("1");

        TextField txtB = new TextField("0");
        
        txtInfo = new TextArea();

        txtInfo.setEditable(false);

        txtInfo.setPrefHeight(100);
        
        txtInfo.setPrefWidth(150);
        
        txtInfo.setWrapText(true);

        Button btnRecta = new Button("Agregar recta");
        
        Button btnLimpiar = new Button("Limpiar");
        
        panelControles.getChildren().addAll(

                new Label("Círculo"),

                new Label("Centro X (h)"),
                txtH,

                new Label("Centro Y (k)"),
                txtK,

                new Label("Radio"),
                txtRadio,

                btnCirculo,

                new Label("----------------"),

                new Label("Recta"),

                new Label("Pendiente (m)"),
                txtM,

                new Label("Intercepto (b)"),
                txtB,

                btnRecta,

                new Label("----------------"),

                btnLimpiar,
                
                txtInfo
        );
        
        setLeft(panelControles);
        
        btnCirculo.setOnAction(e -> {

            double h = Double.parseDouble(txtH.getText());

            double k = Double.parseDouble(txtK.getText());

            double radio = Double.parseDouble(txtRadio.getText());

            Circulo circulo = new Circulo(h, k, radio);

            plano.agregarObjeto(circulo);
            
            Set<InformacionMatematica> lista = plano.getInformacion();
            
            String texto = "";

            for (InformacionMatematica info : lista) {

                texto += info.getTitulo() + "\n";

                texto += info.getDescripcion() + "\n\n";
            }
            
            txtInfo.setText(texto);
        });
        
        btnRecta.setOnAction(e -> {

            double m = Double.parseDouble(txtM.getText());

            double b = Double.parseDouble(txtB.getText());

            Recta recta = new Recta(m, b);

            plano.agregarObjeto(recta);
            
            Set<InformacionMatematica> lista = plano.getInformacion();
            
            String texto = "";

            for (InformacionMatematica info : lista) {

                texto += info.getTitulo() + "\n";

                texto += info.getDescripcion() + "\n\n";
            }
            
            txtInfo.setText(texto);
        });
        
        btnLimpiar.setOnAction(e -> {

            plano.limpiarObjetos();
            
            txtInfo.setText("");
        });
    }
}
