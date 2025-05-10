/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.ConexionBD;

/*public class SesioonController implements Initializable {

    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Button buttonContinuar;
    @FXML
    private Label lblMensaje;
    
    private String rolUsuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializaciones si son necesarias
    }
    
    @FXML
    public void handleLogin(ActionEvent event) {
        String usuario = txtNombreUsuario.getText();
        String contrasena = txtContrasena.getText();
        
        // Consultar el rol del usuario en la base de datos
        String rol = obtenerRolUsuario(usuario, contrasena);
        
        if (rol != null && !rol.isEmpty()) {
            rolUsuario = rol;
            System.out.println("Inicio de sesión exitoso. Rol: " + rolUsuario);
            abrirMenu(event, rolUsuario);  // Redirige y pasa el rol al menú
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos.");
        }
    }
    
    
    //  Consulta la base de datos para obtener el rol de la persona.
     
    private String obtenerRolUsuario(String usuario, String contrasena) {
        String sql = "SELECT r.nombre_rol " +
                     "FROM Personas p " +
                     "JOIN PersonasRoles pr ON p.id_persona = pr.id_persona " +
                     "JOIN Roles r ON pr.id_rol = r.id_rol " +
                     "WHERE p.nombre_usuario = ? AND p.contrasena = ?";
        
        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {
             
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre_rol");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el rol: " + e.getMessage());
        }
        return null;
    }
    
    
    //  Carga la vista del menú principal y le pasa el rol obtenido.
     
    private void abrirMenu(ActionEvent event, String rol) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));
            Parent root = loader.load();
            
            // Se obtiene el controlador del menú para pasarle el rol
            MenuController menuController = loader.getController();
            menuController.setRolUsuario(rol);
            
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(SesioonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
*/





/**
 *
 * @author Karlaa
 */
public class SesioonController implements Initializable {
    
    @FXML
    private Button buttonContinuar;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización de elementos si es necesario
    }   

    
    
    
     @FXML
    private void handleMenu(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(SesioonController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }  
    
    
   }
}
  