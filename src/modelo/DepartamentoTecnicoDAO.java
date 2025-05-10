/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelo.ConexionBD;
/**
 *
 * @author Karlaa
 */
public class DepartamentoTecnicoDAO {
    
 public void asignarTecnicosAlDepartamento(int idDepartamento, List<Integer> idTecnicos) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexionBD.conectar();
            if(conn == null){
                System.err.println("No se pudo establecer la conexión para asignar técnicos.");
                return;
            }
            // Asume que la tabla intermedia se llama "departamentos_tecnicos" con las columnas "iddepartamento" e "idpersona"
            String sql = "INSERT INTO departamentos_tecnicos (iddepartamento, idpersona) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            for(Integer idTecnico : idTecnicos) {
                stmt.setInt(1, idDepartamento);
                stmt.setInt(2, idTecnico);
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { if(stmt != null) stmt.close(); } catch(SQLException ex) { ex.printStackTrace(); }
            try { if(conn != null) conn.close(); } catch(SQLException ex) { ex.printStackTrace(); }
        }
    }
    
    /**
     * Elimina todas las relaciones (técnicos asignados) de un departamento en la tabla intermedia.
     */
    public void eliminarTecnicosPorDepartamento(int idDepartamento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConexionBD.conectar();
            String sql = "DELETE FROM departamentos_tecnicos WHERE iddepartamento = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idDepartamento);
            stmt.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { if(stmt != null) stmt.close(); } catch(SQLException ex) { ex.printStackTrace(); }
            try { if(conn != null) conn.close(); } catch(SQLException ex) { ex.printStackTrace(); }
        }
    }
}
