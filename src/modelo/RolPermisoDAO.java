/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelo.Permiso;
import modelo.ConexionBD;

/**
 *
 * @author mpelv
 */
public class RolPermisoDAO {
  
    // Inserta en la tabla intermedia cada relación rol–permiso.
    public void asignarPermisosAlRol(int idRol, List<Permiso> listaPermisos) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConexionBD.conectar();
            if (conn == null) {
                System.err.println("No se pudo establecer la conexión para asignar permisos.");
                return;
            }
            String sql = "INSERT INTO Roles_Permisos (idrol, idpermiso) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            for (Permiso permiso : listaPermisos) {
                // Suponiendo que permiso.getIdPermiso() retorna el id del permiso.
                pstmt.setInt(1, idRol);
                pstmt.setInt(2, permiso.getIdPermiso());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException ex) {
            System.err.println("Error al asignar permisos al rol: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (pstmt != null) {
                try { pstmt.close(); 
                } catch (SQLException ex) { ex.printStackTrace(); }
            }
            if (conn != null) {
                try { conn.close(); 
                } catch (SQLException ex) { ex.printStackTrace(); }
            }
        }
    }   
    
     public void eliminarPermisosPorRol(int idRol) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConexionBD.conectar();
            if (conn == null) {
                System.err.println("No se pudo establecer la conexión para eliminar permisos.");
                return;
            }
            String sql = "DELETE FROM Roles_Permisos WHERE idrol = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRol);
            int filasEliminadas = pstmt.executeUpdate();
            System.out.println("Se eliminaron " + filasEliminadas + " registros para el rol con id: " + idRol);
        } catch (SQLException ex) {
            System.err.println("Error al eliminar permisos del rol: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

