/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Karlaa
 */
public class GestionTicketsDAO {

    /**
     * Guarda un ticket en la base de datos. 
     * Si ticket.getIdTicket() es 0, se inserta un nuevo registro; de lo contrario, se actualiza.
     */
    public void guardarTicket(Ticket ticket) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionBD.conectar();
            
            if (ticket.getIdTicket() == 0) {
                // INSERT: Se inserta un ticket nuevo. Se asume que el departamento es "Departamento no asignado" para la creación.
                String sql = "INSERT INTO Tickets (titulo, descripcion, idDepartamento, nivelPrioridad, fechaCreacion, idPersonaCreador) " +
                             "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, ?) RETURNING idTicket";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, ticket.getTitulo());
                pstmt.setString(2, ticket.getDescripcion());
                int idDept = obtenerIdDepartamentoPorNombre(ticket.getDepartamento());
                pstmt.setInt(3, idDept);
                pstmt.setString(4, ticket.getPrioridad());
                pstmt.setInt(5, 1); // Ajusta este valor con el id del usuario actual
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    ticket.setIdTicket(rs.getInt("idTicket"));
                }
            } else {
                // UPDATE: Se actualiza un ticket existente
                String sql = "UPDATE Tickets SET titulo = ?, descripcion = ?, idDepartamento = ?, nivelPrioridad = ? " +
                             "WHERE idTicket = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, ticket.getTitulo());
                pstmt.setString(2, ticket.getDescripcion());
                int idDept = obtenerIdDepartamentoPorNombre(ticket.getDepartamento());
                pstmt.setInt(3, idDept);
                pstmt.setString(4, ticket.getPrioridad());
                pstmt.setInt(5, ticket.getIdTicket());
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
    }

    /*
      Obtiene el ID del departamento a partir del nombre.
      Si no se encuentra, retorna un valor por defecto (en este ejemplo, 1).
     */
    private int obtenerIdDepartamentoPorNombre(String nombreDepartamento) {
        int id = 1; // Valor por defecto; ajústalo según tu BD.
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.conectar();
            String sql = "SELECT idDepartamento FROM Departamentos WHERE nombreDepartamento = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreDepartamento);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idDepartamento");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
        return id;
    }

    /**
     * Cierra de manera segura los recursos de JDBC.
     */
    private void cerrarRecursos(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try { 
            if (rs != null) rs.close(); 
        } catch (SQLException e) { e.printStackTrace(); }
        try { 
            if (pstmt != null) pstmt.close(); 
        } catch (SQLException e) { e.printStackTrace(); }
        try { 
            if (conn != null) conn.close(); } 
        catch (SQLException e) { e.printStackTrace(); }
    }
}