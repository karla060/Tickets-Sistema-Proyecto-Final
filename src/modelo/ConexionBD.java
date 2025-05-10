/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Karlaa
 */

public class ConexionBD {
   
    private static final String URL = "jdbc:postgresql://localhost:5432/TicketsSistema";
    private static final String USUARIO = "Administrador";
    private static final String PASSWORD = "admin123";
    
    // Datos de conexión proporcionados
   /* private static final String URL = "jdbc:postgresql://ep-blue-bread-a4xnqcab-pooler.us-east-1.aws.neon.tech:5432/ticketsSistema?sslmode=require";
    private static final String USUARIO = "neondb_owner";
    private static final String PASSWORD = "npg_P23MSOUzpovD";*/

    public static Connection conectar() { 
        Connection conexion = null;
        try {
            Class.forName("org.postgresql.Driver");  // Cargar el driver JDBC
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexion a PostgreSQL realizada correctamente.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return conexion;
    }
}



