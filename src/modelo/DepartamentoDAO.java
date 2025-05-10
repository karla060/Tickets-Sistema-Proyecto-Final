/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Departamento;
import modelo.ConexionBD;
/**
 *
 * @author Karlaa
 */
public class DepartamentoDAO {

     
    // Obtiene TODOS los departamentos almacenados en la BD
   public List<Departamento> obtenerDepartamentos() {
    List<Departamento> lista = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
        conn = ConexionBD.conectar();
        String sql = "SELECT iddepartamento, nombredepartamento, descripciondepartamento FROM departamentos";
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()){
            int id = rs.getInt("iddepartamento");
            String nombre = rs.getString("nombredepartamento");
            String descripcion = rs.getString("descripciondepartamento");
            Departamento depa = new Departamento(nombre, descripcion, "");
            depa.setIdDepartamento(id);
            lista.add(depa);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try { 
            if (rs != null) rs.close(); 
        } catch (SQLException ex) { ex.printStackTrace(); }
        try { 
            if (stmt != null) stmt.close(); 
        } catch (SQLException ex) { ex.printStackTrace(); }
        try { 
            if (conn != null) conn.close(); 
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
    return lista;
}

    
    // Inserta un nuevo departamento en la BD
   public void insertarDepartamento(Departamento depa) {
       Connection conn = null;
        PreparedStatement stmt = null;
    try {
        conn = ConexionBD.conectar();
        // Ahora se inserta en la columna "nombredepartan"
        String sql = "INSERT INTO departamentos (nombredepartamento, descripciondepartamento) VALUES (?, ?)";
        stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, depa.getNombre());
        stmt.setString(2, depa.getDescripcion());
        stmt.executeUpdate();
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()){
            depa.setIdDepartamento(generatedKeys.getInt(1));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try { 
            if (stmt != null) stmt.close(); 
        } catch (SQLException ex) { ex.printStackTrace(); }
        try { 
            if (conn != null) conn.close(); 
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}

    
   public void actualizarDepartamento(Departamento depa) {
      Connection conn = null;
        PreparedStatement stmt = null;
    try {
        conn = ConexionBD.conectar();
        // Se usa "nombredepartan" pues ese es el nombre real en la tabla
        String sql = "UPDATE departamentos SET nombredepartamento = ?, descripciondepartamento = ? WHERE iddepartamento = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, depa.getNombre());
        stmt.setString(2, depa.getDescripcion());
        stmt.setInt(3, depa.getIdDepartamento());
        stmt.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try { 
            if (stmt != null) stmt.close(); 
        } catch (SQLException ex) { ex.printStackTrace(); }
        try { 
            if (conn != null) conn.close(); 
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}

    
    // Elimina un departamento de la BD dado su id
    public void eliminarDepartamento(int idDepartamento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexionBD.conectar();
            String sql = "DELETE FROM Departamentos WHERE iddepartamento = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idDepartamento);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}