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
    private String nombreEmpresa;
    private File logoEmpresa;
    private String idiomaPredeterminado;
    private String zonaHoraria;
    private int tiempoVencimientoTickets;
    private String nivelesPrioridad;

    public SistemaEmpresa() {
    }

    public SistemaEmpresa(String nombreEmpresa, File logoEmpresa, String idiomaPredeterminado, String zonaHoraria, int tiempoVencimientoTickets, String nivelesPrioridad) {
        this.nombreEmpresa = nombreEmpresa;
        this.logoEmpresa = logoEmpresa;
        this.idiomaPredeterminado = idiomaPredeterminado;
        this.zonaHoraria = zonaHoraria;
        this.tiempoVencimientoTickets = tiempoVencimientoTickets;
        this.nivelesPrioridad = nivelesPrioridad;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public File getLogoEmpresa() {
        return logoEmpresa;
    }

    public void setLogoEmpresa(File logoEmpresa) {
        this.logoEmpresa = logoEmpresa;
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

    /*
    // Métodos para validar los datos ingresados
    public boolean validarNombreEmpresa() {
        if (nombreEmpresa == null || nombreEmpresa.isEmpty() || nombreEmpresa.length() < 3 || nombreEmpresa.length() > 100) {
            return false;
        }
        return true;
    }

    public boolean validarLogo() {
        if (logoEmpresa == null || (!logoEmpresa.getName().endsWith(".jpg") && !logoEmpresa.getName().endsWith(".png")) || logoEmpresa.length() > 2 * 1024 * 1024) {
            return false;
        }
        return true;
    }

    public boolean validarIdioma() {
        List<String> idiomasAceptados = Arrays.asList("Español", "Inglés");
        if (!idiomasAceptados.contains(idiomaPredeterminado)) {
            return false;
        }
        return true;
    }

    public boolean validarZonaHoraria() {
        List<String> zonasHorariasSoportadas = Arrays.asList("Guatemala Guatemala", "Denver EEUU");
        if (!zonasHorariasSoportadas.contains(zonaHoraria)) {
            return false;
        }
        return true;
    }

    public boolean validarTiempoVencimiento() {
        if (tiempoVencimientoTickets < 1 || tiempoVencimientoTickets > 365) {
            return false;
        }
        return true;
    }

    public boolean validarNivelesPrioridad() {
        List<String> nivelesAceptados = Arrays.asList("Alta", "Media", "Baja");
        if (!nivelesAceptados.contains(nivelesPrioridad)) {
            return false;
        }
        return true;
    }

    public boolean validarDatos() {
        return validarNombreEmpresa() &&
               validarLogo() &&
               validarIdioma() &&
               validarZonaHoraria() &&
               validarTiempoVencimiento() &&
               validarNivelesPrioridad();
    }*/
}
