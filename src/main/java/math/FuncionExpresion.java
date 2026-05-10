
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jegor InfinitySoftware
 */
package math;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class FuncionExpresion extends Funcion {

    private String expresion;

    public FuncionExpresion(String expresion) {

        this.expresion = expresion;
    }

    @Override
    public double evaluar(double x) {

        Expression e = new ExpressionBuilder(expresion)
                .variable("x")
                .build()
                .setVariable("x", x);

        return e.evaluate();
    }
}
