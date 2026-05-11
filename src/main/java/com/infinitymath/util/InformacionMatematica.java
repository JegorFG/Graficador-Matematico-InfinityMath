/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infinitymath.util;

import java.util.Objects;

/**
 *
 * @author Jegor InfinitySoftware
 */
public class InformacionMatematica {

    private String titulo;

    private String descripcion;

    public InformacionMatematica(String titulo, String descripcion) {

        this.titulo = titulo;
        this.descripcion = descripcion;
    }
    
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {

            return false;
        }

        InformacionMatematica other = (InformacionMatematica) obj;

        return titulo.equals(other.titulo) && descripcion.equals(other.descripcion);
    }
    
    @Override
    public int hashCode() {

        return Objects.hash(titulo, descripcion);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
