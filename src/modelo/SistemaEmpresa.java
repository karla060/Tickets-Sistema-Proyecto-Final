/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import java.util.Arrays;
import java.io.File;

/**
 *
 * @author Karlaa
 */
public class SistemaEmpresa {
 
   /*private int id;*/
    private String nombreEmpresa;
    
    private byte[] logoEmpresa;
    private String tipoLogoEmpresa;  // Por ejemplo: "JPG" o "PNG"
    private String idiomaPredeterminado;
    private String zonaHoraria;
  
    private int tiempoVencimientoTickets;
    private String nivelesPrioridad;
    
    
 
    public SistemaEmpresa() {
    }
    
      public SistemaEmpresa(/*int id,*/ String nombreEmpresa, byte[] logoEmpresa, String tipoLogoEmpresa, String idiomaPredeterminado, String zonaHoraria, int tiempoVencimientoTickets, String nivelesPrioridad) {
        /*this.id = id;*/
        this.nombreEmpresa = nombreEmpresa;
        this.logoEmpresa = logoEmpresa;
        this.tipoLogoEmpresa = tipoLogoEmpresa;
        this.idiomaPredeterminado = idiomaPredeterminado;
        this.zonaHoraria = zonaHoraria;
        this.tiempoVencimientoTickets = tiempoVencimientoTickets;
        this.nivelesPrioridad = nivelesPrioridad;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public byte[] getLogoEmpresa() {
        return logoEmpresa;
    }

    public void setLogoEmpresaBytes(byte[] logoEmpresa) {
        this.logoEmpresa = logoEmpresa;
    }

    public String getTipoLogoEmpresa() {
        return tipoLogoEmpresa;
    }

    public void setTipoLogoEmpresa(String tipoLogoEmpresa) {
        this.tipoLogoEmpresa = tipoLogoEmpresa;
    }

    public String getIdiomaPredeterminado() {
        return idiomaPredeterminado;
    }

    public void setIdiomaPredeterminado(String idiomaPredeterminado) {
        this.idiomaPredeterminado = idiomaPredeterminado;
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public void setZonaHoraria(String zonaHoraria) {
        this.zonaHoraria = zonaHoraria;
    }

    public int getTiempoVencimientoTickets() {
        return tiempoVencimientoTickets;
    }

    public void setTiempoVencimientoTickets(int tiempoVencimientoTickets) {
        this.tiempoVencimientoTickets = tiempoVencimientoTickets;
    }

    public String getNivelesPrioridad() {
        return nivelesPrioridad;
    }

    public void setNivelesPrioridad(String nivelesPrioridad) {
        this.nivelesPrioridad = nivelesPrioridad;
    }

  
}