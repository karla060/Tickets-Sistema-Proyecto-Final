/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Karlaa
 */
package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Permiso;
import modelo.ConexionBD;

public class PermisoDAO {

   // Método para obtener todos los permisos almacenados en la tabla Permisos
    public List<Permiso> obtenerPermisos() {
        List<Permiso> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.conectar();
            // Consulta: asegúrate de que los nombres de la tabla y las columnas coincidan con tu BD.
            String sql = "SELECT nombrepermiso, descripcionpermiso FROM Permisos";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombrepermiso");
                String descripcion = rs.getString("descripcionpermiso");
                lista.add(new Permiso(nombre, descripcion));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { 
                if (rs != null) rs.close(); 
            } catch (SQLException ex) { ex.printStackTrace(); }
            try { 
                if (pstmt != null) pstmt.close(); 
            } catch (SQLException ex) { ex.printStackTrace(); }
            try { 
                if (conn != null) conn.close(); 
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return lista;
    }

    // Método para insertar un nuevo permiso en la tabla Permisos.
    public void insertarPermiso(Permiso permiso) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConexionBD.conectar();
            String sql = "INSERT INTO Permisos (nombrepermiso, descripcionpermiso) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, permiso.getNombrePermiso());
            pstmt.setString(2, permiso.getDescripcionPermiso());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { 
                if (pstmt != null) pstmt.close(); 
            } catch (SQLException ex) { ex.printStackTrace(); }
            try { 
                if (conn != null) conn.close(); 
            } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
    
    public int obtenerIdPermisoPorNombre(String nombre) {
        int id = -1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.conectar();
            if (conn == null) {
                System.err.println("No se pudo establecer la conexión para obtener el id del permiso.");
                return id;
            }
            // Ajusta el nombre de la tabla y columna según tu esquema
            String sql = "SELECT idpermiso FROM Permisos WHERE nombrepermiso = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            rs = pstmt.executeQuery();
            if (rs.next()){
                id = rs.getInt("idpermiso");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
        return id;
    }
    
    
}