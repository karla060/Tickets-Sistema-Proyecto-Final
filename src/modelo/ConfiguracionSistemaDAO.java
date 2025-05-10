/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Karlaa
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfiguracionSistemaDAO {


    /**
     * Persiste la configuración de la empresa en la base de datos.
     * Se utiliza la tabla "empresa" con los campos:
     * nombreEmpresa, logoEmpresa, tipoLogoEmpresa, idiomaPredeterminado, zonaHoraria, tiempoVencimiento, nivelesPrioridad.
     */
    public static boolean guardarConfiguracion(SistemaEmpresa empresa) {
        Connection conexion = ConexionBD.conectar();
        String sql = "INSERT INTO empresa (nombreEmpresa, logoEmpresa, tipoLogoEmpresa, idiomaPredeterminado, zonaHoraria, tiempoVencimiento, nivelesPrioridad) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            // 1. Asignar el nombre de la empresa.
            ps.setString(1, empresa.getNombreEmpresa());
            
            // 2. Asignar los datos binarios del logo.
            // Se asume que empresa.getLogoEmpresa() retorna un arreglo de bytes con la imagen.
            ps.setBytes(2, empresa.getLogoEmpresa());
            
            // 3. Asignar el tipo de logo (por ejemplo, "JPG" o "PNG").
            ps.setString(3, empresa.getTipoLogoEmpresa());
            
            // 4. Asignar el idioma predeterminado.
            ps.setString(4, empresa.getIdiomaPredeterminado());
            
            // 5. Asignar la zona horaria.
            ps.setString(5, empresa.getZonaHoraria());
            
            // 6. Asignar el tiempo de vencimiento de tickets.
            ps.setInt(6, empresa.getTiempoVencimientoTickets());
            
            // 7. Asignar los niveles de prioridad.
            ps.setString(7, empresa.getNivelesPrioridad());
            
            // Ejecutar la consulta.
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al guardar configuración: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   

    // Método para obtener la configuración registrada en la tabla "empresa"
    public static SistemaEmpresa obtenerConfiguracion() {
        SistemaEmpresa cs = null;
        Connection conexion = ConexionBD.conectar();
        // Consulta usando los nombres de columnas de tu nueva tabla
        String sql = "SELECT nombreEmpresa, logoEmpresa, tipoLogoEmpresa, idiomaPredeterminado, zonaHoraria, tiempoVencimiento, nivelesPrioridad FROM empresa LIMIT 1";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                cs = new SistemaEmpresa();
                cs.setNombreEmpresa(rs.getString("nombreEmpresa"));
                // Se obtiene el arreglo de bytes para el logo (columna BYTEA)
                cs.setLogoEmpresaBytes(rs.getBytes("logoEmpresa"));
                cs.setTipoLogoEmpresa(rs.getString("tipoLogoEmpresa"));
                cs.setIdiomaPredeterminado(rs.getString("idiomaPredeterminado"));
                cs.setZonaHoraria(rs.getString("zonaHoraria"));
                cs.setTiempoVencimientoTickets(rs.getInt("tiempoVencimiento"));
                cs.setNivelesPrioridad(rs.getString("nivelesPrioridad"));
            }
            
            rs.close();
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener configuración: " + e.getMessage());
            e.printStackTrace();
        }
        
        return cs;
    }
}