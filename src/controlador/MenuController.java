/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Karlaa
 */
public class MenuController implements Initializable {

    @FXML
    private Button buttonConfiguracionSistema;
    @FXML
    private Button buttonRolesYPermisos;
    @FXML
    private Button buttonGestionDepartamentos;
    @FXML
    private Button buttonGestionUsuarios;
    @FXML
    private Button buttonGestionEstadosTickets;
    @FXML
    private Button buttonGestionFlujosTrabajos;
     @FXML
    private Button buttonGestionTickets;
     @FXML
    private Button buttonUsuarioMisTickets;
     @FXML
    private Button buttonTecnicoTicketsPendientes;
     @FXML
    private Button buttonAdmiGestionTickets; 
    @FXML
    private Button buttonRegresar;  
    //buttonRolesYPermisos   handleRolesYpermisos
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleConfiguracionSistema (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ConfiguracionSistemaVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(MenuController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    }
    
    @FXML
    private void handleRolesYpermisos (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionRolesPermisosVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(MenuController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    }
    
     @FXML
    private void handleGestionDepartamentos (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionDepartamentosVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(MenuController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    }
    
     @FXML
    private void handleGestionUsuarios (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionUsuarioVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(MenuController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    }
    
     @FXML
    private void handleGestionEstadosTickets (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionEstadosTicketsVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(MenuController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    }
    
    @FXML
    private void handleGestionFlujos (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionFlujosTrabajoVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
           
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(MenuController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    }
    
    @FXML
    private void handleGestionTickets (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionTicketVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(MenuController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    }
    
    @FXML
    private void handleMisTickets (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MisTicketsVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(MenuController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    }
    
    
    @FXML
    private void handleTicketsPendientes (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/TicketsPendientesVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(MenuController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    } 
    
    @FXML
    private void handleAdmiGestionTickets (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/AdminGestionTicketsPendientes.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(MenuController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    } 
    
    @FXML
    private void handleInicioSesión (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/SesioonVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(MenuController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    } 
    
}