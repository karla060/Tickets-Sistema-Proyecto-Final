/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;
import java.util.Properties;
import java.sql.Connection;
import java.util.logging.Level; 
import java.util.logging.Logger;
/**
 *
 * @author Karlaa
 */

 
   /* private String url = "jdbc:postgresql://ep-blue-bread-a4xnqcab-pooler.us-east-1.aws.neon.tech/Universidad";   */

/*public class ConexionBD {
   

    private String url = "jdbc:postgresql://ep-blue-bread-a4xnqcab-pooler.us-east-1.aws.neon.tech/ticketsSistema";
    private Properties properties = new Properties ();
    
    private static Connection conn = null;
  
    //patrones de disseño = singletool
    //constructor privado para que ninguna clase externa a esta pueda acceder a este constructor
    
    private ConexionBD() {
    properties.setProperty("user", "neondb_owner");
    properties.setProperty("password", "npg_P23MSOUzpovD");
    
        try {
            conn = DriverManager.getConnection(url, properties);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection conectar (){
    if (conn == null){
     ConexionBD conexion = new ConexionBD ();
     return conexion.conn;
    } else {
       return conn;
    }    
    }
}*/

public class ConexionBD {
  

    // Datos de conexión proporcionados
    private static final String URL = "jdbc:postgresql://ep-blue-bread-a4xnqcab-pooler.us-east-1.aws.neon.tech:5432/ticketsSistema?sslmode=require";
    private static final String USUARIO = "neondb_owner";
    private static final String PASSWORD = "npg_P23MSOUzpovD";

    
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



/* 
    private static final String URL = "jdbc:postgresql://localhost:5432/TicketsSistema";
    private static final String USUARIO = "Administrador";
    private static final String PASSWORD = "admin123";*/

