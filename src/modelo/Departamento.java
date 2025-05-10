/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author Karlaa
 */
public class Departamento {
    private int idDepartamento; 
    private String nombre; // Nombre del departamento
    private String descripcion; // Descripción del departamento
    private String tecnicosAsignados; // Lista de técnicos asignados al departamento como texto
    private boolean tieneTicketsActivos; // Para controlar si el departamento tiene tickets activos

    // Constructor
    public Departamento(String nombre, String descripcion, String tecnicosAsignados) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tecnicosAsignados = tecnicosAsignados;
        // Por defecto, debe ser false porque no tiene tickets activos inicialmente
        this.tieneTicketsActivos = false; 
    }

    // Getters y Setters de los atributos

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
    
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTecnicosAsignados() {
        return tecnicosAsignados;
    }

    public void setTecnicosAsignados(String tecnicosAsignados) {
        this.tecnicosAsignados = tecnicosAsignados;
    }

    public boolean tieneTicketsActivos() {
        return tieneTicketsActivos;
    }

    public void setTieneTicketsActivos(boolean tieneTicketsActivos) {
        this.tieneTicketsActivos = tieneTicketsActivos;
    }
    
/*
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.nombre);
        hash = 53 * hash + Objects.hashCode(this.descripcion);
        hash = 53 * hash + Objects.hashCode(this.tecnicosAsignados);
        hash = 53 * hash + (this.tieneTicketsActivos ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departamento other = (Departamento) obj;
        if (this.tieneTicketsActivos != other.tieneTicketsActivos) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return Objects.equals(this.tecnicosAsignados, other.tecnicosAsignados);
    }*/
    
    
    
}