/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import modelo.Usuario;
import modelo.ConexionBD;
/**
 *
 * @author mpelv
 */
public class UsuarioDAO {
 

    // Inserta un nuevo usuario en la tabla "usuarios" (ajusta los nombres de las columnas según tu esquema)
    public void insertarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexionBD.conectar();
            String sql = "INSERT INTO usuarios (nombrecompleto, email, nombreusuario, contrasena, rol, departamento) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNombreCompleto());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getNombreUsuario());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, usuario.getRol());
            stmt.setString(6, usuario.getDepartamento());
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                usuario.setIdUsuario(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    // Actualiza un usuario existente en la base de datos
    public void actualizarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexionBD.conectar();
            String sql = "UPDATE usuarios SET nombrecompleto = ?, email = ?, nombreusuario = ?, contrasena = ?, rol = ?, departamento = ? WHERE idusuario = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNombreCompleto());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getNombreUsuario());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, usuario.getRol());
            stmt.setString(6, usuario.getDepartamento());
            stmt.setInt(7, usuario.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}   

