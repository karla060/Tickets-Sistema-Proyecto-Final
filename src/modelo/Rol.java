/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/*
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;*/

/**
 *
 * @author Karlaa
 */


public class Rol {
    private String nombreRol;
    private String descripcionRol;
    private List<Permiso> permisos;

    // Constructor vacío
    public Rol() {
        this.permisos = new ArrayList<>(); // Inicializa la lista para evitar valores null
    }

    // Constructor con parámetros
    public Rol(String nombreRol, String descripcionRol, List<Permiso> permisos) {
        this.nombreRol = nombreRol;
        this.descripcionRol = descripcionRol;
        this.permisos = (permisos != null) ? permisos : new ArrayList<>();
    }

    // Getters y setters
    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = (permisos != null) ? permisos : new ArrayList<>();
    }

    // Método para mostrar los nombres de los permisos como un String concatenado
    public String getPermisosAsignados() {
        if (permisos == null || permisos.isEmpty()) {
            return "Sin permisos asignados";
        }
        return permisos.stream()
            .map(Permiso::getNombrePermiso) // Obtiene el nombre de cada permiso
            .collect(Collectors.joining(", ")); // Junta los nombres con comas
    }

    
    
    // Método opcional para depuración
     // Método opcional para depuración
    @Override
    public String toString() {
        return String.format("Rol: %s, Descripción: %s, Permisos: %s",
                nombreRol, descripcionRol, getPermisosAsignados());
    }
}
