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

public class ConexionBD {
   
  
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
}

