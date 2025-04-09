/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import modelo.Permiso;



/**
 * FXML Controller class
 *
 * @author Karlaa
 *   @FXML
    
 */
 
/*private void handleCrearRol(ActionEvent event) {
@FXML
 private void hansdleguardarRol(ActionEvent event) {
 @FXML
 private void handleGuardarRol(ActionEvent event) {

*/
public class GestionRolesPermisosController implements Initializable {    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización general si es necesaria
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
             java.util.logging.Logger.getLogger(GestionRolesPermisosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    }
    
    
     @FXML 
     private void handleRoles (ActionEvent event) {
         
         
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionRolesPermisos2Vista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionRolesPermisosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }   
    
      @FXML 
     private void handlePermisos (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionRolesPermisos3Vista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionRolesPermisosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }  
    
}
/*
    @FXML
    private void handleIrGestionRoles(ActionEvent event) {
        // Navegar a la vista de gestión de roles
        cambiarVista(event, "/vista/GestionRolesVista.fxml", "Gestión de Roles");
    }

    @FXML
    private void handleIrGestionPermisos(ActionEvent event) {
        // Navegar a la vista de gestión de permisos
        cambiarVista(event, "/vista/GestionPermisosVista.fxml", "Gestión de Permisos");
    }

    @FXML
    private void handleRegresarMenuPrincipal(ActionEvent event) {
        // Navegar al menú principal u otra vista raíz
        cambiarVista(event, "/vista/MenuPrincipal.fxml", "Menú Principal");
    }

    private void cambiarVista(ActionEvent event, String rutaFXML, String tituloVentana) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(tituloVentana);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Muestra una alerta en caso de error de navegación
            mostrarError("No se pudo cargar la vista: " + rutaFXML);
        }
    }

    private void mostrarError(String mensaje) {
        // Mostrar un diálogo de error (alerta)
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }*/


    
    



    
    /*  @FXML 
     private void handleVentana (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionRolesPermisosVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionRolesPermisosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
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
             java.util.logging.Logger.getLogger(GestionRolesPermisosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }
}
*/


    /*
    @FXML 
     private void handleCrearPermiso(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionRolesPermisos3Vista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionRolesPermisosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }  
    
    
     @FXML 
     private void handleRol(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionRolesPermisos2Vista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionRolesPermisosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }  
    
     @FXML 
     private void handleVentana (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionRolesPermisosVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionRolesPermisosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
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
             java.util.logging.Logger.getLogger(GestionRolesPermisosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }
    
}  */
    