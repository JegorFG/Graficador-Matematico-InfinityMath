
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jegor InfinitySoftware
 */
package com.infinitymath.math;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Clase que representa una función matemática definida mediante
 * una expresión escrita en texto.
 * 
 * Ejemplo de expresiones válidas:
 * "x^2 + 3*x - 5"
 * "sin(x)"
 * "sqrt(x)"
 * 
 * Esta clase hereda de la clase Funcion y sobrescribe
 * el método evaluar() para calcular el resultado de la expresión.
 */
public class FuncionExpresion extends Funcion {

    /**
     * Expresión matemática que será evaluada.
     */
    private String expresion;

    /**
     * Constructor de la clase.
     * 
     * Recibe una expresión matemática en formato String
     * y la almacena para posteriormente evaluarla.
     * 
     * @param expresion Expresión matemática a evaluar.
     */
    public FuncionExpresion(String expresion) {

        this.expresion = expresion;
    }

    /**
     * Evalúa la expresión matemática sustituyendo
     * el valor de la variable x.
     * 
     * Ejemplo:
     * Si la expresión es "x^2 + 1" y x = 2,
     * el resultado será 5.
     * 
     * @param x Valor que tomará la variable x.
     * @return Resultado numérico de la evaluación.
     */
    @Override
    public double evaluar(double x) {

        // Construye la expresión matemática
        Expression e = new ExpressionBuilder(expresion)

                // Declara que la expresión utilizará la variable x
                .variable("x")

                // Construye la expresión lista para evaluarse
                .build()

                // Asigna el valor recibido al símbolo x
                .setVariable("x", x);

        // Evalúa y retorna el resultado final
        return e.evaluate();
    }
}