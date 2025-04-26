/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Administrador;
import modelo.Persona;
import modelo.Tecnico;
import modelo.Usuario;



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
   /* @FXML
    private Button buttonContinuar;
    @FXML
    private TextField textFieldUsuario;
    @FXML
    private PasswordField passwordFieldContrasena;

    private Persona uss; // Usuario autenticado

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización de elementos si es necesario
    }   

    @FXML
    private void handleMenu(ActionEvent event) {
        String nombreUsuario = textFieldUsuario.getText();
        String contrasena = passwordFieldContrasena.getText();

        if (validarContrasena(nombreUsuario, contrasena)) {
            cargarMenuPrincipal(event); // Redirigir al menú con el usuario autenticado
        } else {
            mostrarMensajeError("Credenciales incorrectas. Por favor, inténtelo nuevamente.");
        }
    }

    private boolean validarContrasena(String nombreUsuario, String contrasena) {
        // Simulación: Esto normalmente vendría de una base de datos
        if (nombreUsuario.equals("admin1") && contrasena.equals("admin123")) {
            uss = new Administrador("Ana Pérez", "ana@gmail.com", "admin1", "admin123");
            return true;
        } else if (nombreUsuario.equals("tecnico1") && contrasena.equals("tecnico456")) {
            uss = new Tecnico("Luis Gómez", "luis@gmail.com", "tecnico1", "tecnico456", "Sistemas");
            return true;
        } else if (nombreUsuario.equals("usuario1") && contrasena.equals("usuario789")) {
            uss = new Usuario("Carlos Martínez", "carlos@gmail.com", "usuario1", "usuario789", "Ventas");
            return true;
        }//
        return false;
    }

    private void cargarMenuPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del menú
            MenuController menuController = loader.getController();
            menuController.setUsuarioLogueado(uss); // Pasar el usuario autenticado

            // Configurar la escena y mostrarla
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            mostrarMensajeError("Error al cargar el menú.");
        }
    }

    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Inicio de Sesión");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }*/
    
    

