/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author chuss
 */
package math;

public class FuncionCuadratica extends Funcion {

    private double a;
    private double b;
    private double c;

    public FuncionCuadratica(double a, double b, double c) {

        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double evaluar(double x) {

        return (a * Math.pow(x, 2)) + (b * x) + c;
    }
}