/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Rol;
import modelo.ConexionBD;


/**
 *
 * @author Karlaa
 */
public class RolDAO {
    
    // Obtiene todos los roles de la base de datos TicketsSistema
    public List<Rol> obtenerRoles() {
        List<Rol> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.conectar();
            // Aseg\u00FArate de que los nombres de tabla y columnas concuerden con tu esquema.
            String sql = "SELECT idrol, nombrerol, descripcionrol FROM Roles";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("idrol");
                String nombre = rs.getString("nombrerol");
                String descripcion = rs.getString("descripcionrol");
                // Al obtener roles, no estamos cargando los permisos (puedes implementarlo luego si hace falta)
                Rol rol = new Rol(nombre, descripcion, new ArrayList<>());
                rol.setIdRol(id);
                lista.add(rol);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch(SQLException ex){ ex.printStackTrace(); }
            try { if (pstmt != null) pstmt.close(); } catch(SQLException ex){ ex.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch(SQLException ex){ ex.printStackTrace(); }
        }
        return lista;
    }
    
    // Inserta un nuevo rol en la base de datos.
    public void insertarRol(Rol rol) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConexionBD.conectar();
            // La consulta inserta el nombre y la descripción. Se asume que los permisos se manejan aparte.
            String sql = "INSERT INTO Roles (nombrerol, descripcionrol) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, rol.getNombreRol());
            pstmt.setString(2, rol.getDescripcionRol());
            pstmt.executeUpdate();
            // Recupera el ID generado (si la tabla tiene autoincremento)
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()){
                rol.setIdRol(generatedKeys.getInt(1));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { 
                if (pstmt != null) pstmt.close(); 
            } catch(SQLException ex){ ex.printStackTrace(); }
            try { 
                if (conn != null) conn.close(); 
            } catch(SQLException ex){ ex.printStackTrace(); }
        }
    }
    
    // Actualiza los datos de un rol existente.
    public void actualizarRol(Rol rol) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConexionBD.conectar();
            String sql = "UPDATE Roles SET nombrerol = ?, descripcionrol = ? WHERE idrol = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rol.getNombreRol());
            pstmt.setString(2, rol.getDescripcionRol());
            pstmt.setInt(3, rol.getIdRol());
            pstmt.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch(SQLException ex){ ex.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch(SQLException ex){ ex.printStackTrace(); }
        }
    }
    
    // Elimina un rol de la base de datos.
    public void eliminarRol(int idRol) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ConexionBD.conectar();
            String sql = "DELETE FROM Roles WHERE idrol = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idRol);
            pstmt.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { if (pstmt != null) pstmt.close(); } catch(SQLException ex){ ex.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch(SQLException ex){ ex.printStackTrace(); }
        }
    }
}
