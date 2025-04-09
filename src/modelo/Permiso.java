/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;



/**
 *
 * @author Karlaa
 */


public class Permiso {
    private String nombrePermiso;
    private String descripcionPermiso;

    // Constructor vacío
    public Permiso() {
    }

    // Constructo con parámetros
    public Permiso(String nombrePermiso, String descripcionPermiso) {
        this.nombrePermiso = nombrePermiso;
        this.descripcionPermiso = descripcionPermiso;
    }

    // Getters y setters
    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }

    public String getDescripcionPermiso() {
        return descripcionPermiso;
    }

    public void setDescripcionPermiso(String descripcionPermiso) {
        this.descripcionPermiso = descripcionPermiso;
    }

    // Método opcional para depuración
    @Override
    public String toString() {
        return String.format("Permiso: %s, Descripción: %s", nombrePermiso, descripcionPermiso);
    }
}

