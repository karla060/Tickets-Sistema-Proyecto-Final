/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Karlaa
 */


public class Usuario {
    private String nombreCompleto; // Nombre completo del usuario
    private String email;          // Correo electrónico del usuario
    private String nombreUsuario;  // Nombre de usuario único
    private String contrasena;     // Contraseña del usuario
    private String rol;            // Rol asignado al usuario (Administrador, Técnico, etc.)
    private String departamento;   // Departamento del usuario (opcional, solo para Técnicos)

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(String nombreCompleto, String email, String nombreUsuario, String contrasena, String rol, String departamento) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.departamento = departamento; // Puede ser null o vacío si no aplica
    }

    // Getters y Setters
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    // Método para mostrar información del usuario (opcional para depuración)
    @Override
    public String toString() {
        return String.format(
            "Usuario: %s, Email: %s, Nombre de Usuario: %s, Rol: %s, Departamento: %s",
            nombreCompleto, email, nombreUsuario, rol, (departamento == null || departamento.isEmpty() ? "N/A" : departamento)
        );
    }
}
