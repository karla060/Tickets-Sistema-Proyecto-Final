/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author  Karlaa
 */
public class FlujoTrabajo {
    private String nombreFlujo;
    private String estadosInvolucrados;
    private String transicionesPermitidas;
    private String reglasDeTransicion;
    private String accionesAutomaticas;

    // Constructor
    public FlujoTrabajo(String nombreFlujo, String estadosInvolucrados, String transicionesPermitidas, String reglasDeTransicion, String accionesAutomaticas) {
        this.nombreFlujo = nombreFlujo;
        this.estadosInvolucrados = estadosInvolucrados;
        this.transicionesPermitidas = transicionesPermitidas;
        this.reglasDeTransicion = reglasDeTransicion;
        this.accionesAutomaticas = accionesAutomaticas;
    }

      // Getters y Setters
    public String getNombreFlujo() { 
       return nombreFlujo; 
    }
    
    public void setNombreFlujo(String nombreFlujo) { 
        this.nombreFlujo = nombreFlujo; 
    }

    public String getEstadosInvolucrados() {
        return estadosInvolucrados; 
    }
    
    public void setEstadosInvolucrados(String estadosInvolucrados) { 
        this.estadosInvolucrados = estadosInvolucrados; 
    }
    
    public String getReglasDeTransicion() {
        return reglasDeTransicion;
    }

    public void setReglasDeTransicion(String reglasDeTransicion) {
        this.reglasDeTransicion = reglasDeTransicion;
    }

    public String getAccionesAutomaticas() {
        return accionesAutomaticas;
    }

    public void setAccionesAutomaticas(String accionesAutomaticas) {
        this.accionesAutomaticas = accionesAutomaticas;
    }

     public String getTransicionesPermitidas() {
        return transicionesPermitidas;
    }

    public void setTransicionesPermitidas(String transicionesPermitidas) {
        this.transicionesPermitidas = transicionesPermitidas;
    }
   
}