/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Karlaa
 */

public class Persona {
    private String nombreCompleto; // Nombre completo del usuario
    private String email;          // Correo electrónico del usuario
    private String nombreUsuario;  // Nombre de usuario único
    private String contrasena;     // Contraseña del usuario
    private String rol;            // Rol asignado al usuario (Administrador, Técnico, etc.)
    private String departamento;   // Departamento del usuario (opcional, solo para Técnicos)

    // Constructor
    public Persona(String nombreCompleto, String email, String nombreUsuario, String contrasena, String rol, String departamento) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.departamento = departamento;
    }

    // Constructor sin departamento (para roles que no lo requieran)
    public Persona(String nombreCompleto, String email, String nombreUsuario, String contrasena, String rol) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.departamento = null; // No aplica para roles como Administrador o Usuario
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

    // Método para mostrar información básica del usuario
    public void mostrarInformacion() {
        System.out.println("Nombre Completo: " + nombreCompleto);
        System.out.println("Email: " + email);
        System.out.println("Nombre de Usuario: " + nombreUsuario);
        System.out.println("Rol: " + rol);
        if (departamento != null) {
            System.out.println("Departamento: " + departamento);
        } else {
            System.out.println("Departamento: No aplica para este rol.");
        }
    }

    // Método para verificar si el usuario pertenece a un rol específico
    /*public boolean esRol(String rolBuscado) {
        return this.rol.equalsIgnoreCase(rolBuscado);
    }*/
    
    
}
