/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.ConexionBD;
/**
 *
 * @author Karlaa
 */
public class TecnicoDAO {
    /**
     * @param nombre El nombre del técnico.
     * @return El id del técnico o -1 si no se encuentra.
     */
    public int obtenerIdTecnicoPorNombre(String nombre) {
        int id = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.conectar();
            String sql = "SELECT idpersona FROM persona WHERE nombre = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("idpersona");
            }
        } catch (SQLException ex) {
            System.err.println("Error al obtener el id del técnico: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try { 
                if (rs != null) rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return id;
    }
}
