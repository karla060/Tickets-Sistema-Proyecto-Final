/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Karlaa
 */
public class EstadoTicket {
    private String nombreEstado;
    private String descripcionEstado;
    private String estadoFinal;
    private String estadosSiguientes;

    // Constructor
    public EstadoTicket(String nombreEstado, String descripcionEstado, String estadoFinal, String estadosSiguientes) {
        this.nombreEstado = nombreEstado;
        this.descripcionEstado = descripcionEstado;
        this.estadoFinal = estadoFinal;
        this.estadosSiguientes = estadosSiguientes;
    }

    // Getters y Setters
    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public String getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(String estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public String getEstadosSiguientes() {
        return estadosSiguientes;
    }

    public void setEstadosSiguientes(String estadosSiguientes) {
        this.estadosSiguientes = estadosSiguientes;
    }
}
