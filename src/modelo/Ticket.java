/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Karlaa
 */
public class Ticket {
    private String titulo;
    private String descripcion;
    private String departamento;
    private String prioridad;

    public Ticket(String titulo, String descripcion, String departamento, String prioridad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.departamento = departamento;
        this.prioridad = prioridad;
    }

    public String getTitulo() { 
        return titulo; 
    }
    
    public void setTitulo(String titulo) { 
        this.titulo = titulo; 
    }

    public String getDescripcion() { 
        return descripcion; 
    }
    
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }

    public String getDepartamento() { 
        return departamento; 
    }
    
    public void setDepartamento(String departamento) { 
        this.departamento = departamento; 
    }

    public String getPrioridad() { 
        return prioridad; 
    }
    
    public void setPrioridad(String prioridad) { 
        this.prioridad = prioridad; 
    }
}

