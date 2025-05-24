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
    /* @FXML
    private Button buttonTecnicoTicketsPendientes;
     @FXML
    private Button buttonAdmiGestionTickets; 
    @FXML*/
    private Button buttonRegresar;  
    //buttonRolesYPermisos   handleRolesYpermisos
    private String rolUsuario;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void handleConfiguracionSistema (ActionEvent event) {
            
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
    public void handleRolesYpermisos (ActionEvent event) {
            
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
    public void handleGestionDepartamentos (ActionEvent event) {
            
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
    public void handleGestionUsuarios (ActionEvent event) {
            
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
    public void handleGestionEstadosTickets (ActionEvent event) {
            
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
    public void handleGestionFlujos (ActionEvent event) {
            
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
    public void handleGestionTickets (ActionEvent event) {
            
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
    public void handleMisTickets (ActionEvent event) {
            
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
    public void handleTicketsPendientes (ActionEvent event) {
            
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
    public void handleAdmiGestionTickets (ActionEvent event) {
            
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
    public void handleInicioSesión (ActionEvent event) {
            
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
    
    
   /* public void setRolUsuario(String rolUsuario) {
    this.rolUsuario = rolUsuario;
    configurarAccesos(); // Ajustar accesos según el rol
}

private void configurarAccesos() {
    switch (rolUsuario) {
        case "Administrador":
            buttonConfiguracionSistema.setDisable(false);
            buttonRolesYPermisos.setDisable(false);
            buttonGestionDepartamentos.setDisable(false);
            buttonGestionUsuarios.setDisable(false);
            buttonGestionEstadosTickets.setDisable(false);
            buttonGestionFlujosTrabajos.setDisable(false);
            buttonGestionTickets.setDisable(false);
            buttonAdmiGestionTickets.setDisable(false);
            buttonUsuarioMisTickets.setDisable(true);
            buttonTecnicoTicketsPendientes.setDisable(true);
            break;
        case "Tecnico":
            buttonConfiguracionSistema.setDisable(true);
            buttonRolesYPermisos.setDisable(true);
            buttonGestionDepartamentos.setDisable(true);
            buttonGestionUsuarios.setDisable(true);
            buttonGestionEstadosTickets.setDisable(false);
            buttonGestionFlujosTrabajos.setDisable(false);
            buttonGestionTickets.setDisable(false);
            buttonTecnicoTicketsPendientes.setDisable(false);
            buttonAdmiGestionTickets.setDisable(true);
            buttonUsuarioMisTickets.setDisable(true);
            break;
        case "Usuario":
            buttonConfiguracionSistema.setDisable(true);
            buttonRolesYPermisos.setDisable(true);
            buttonGestionDepartamentos.setDisable(true);
            buttonGestionUsuarios.setDisable(true);
            buttonGestionEstadosTickets.setDisable(true);
            buttonGestionFlujosTrabajos.setDisable(true);
            buttonGestionTickets.setDisable(true);
            buttonUsuarioMisTickets.setDisable(false);
            buttonTecnicoTicketsPendientes.setDisable(true);
            buttonAdmiGestionTickets.setDisable(true);
            break;
        default:
            buttonConfiguracionSistema.setDisable(true);
            buttonRolesYPermisos.setDisable(true);
            buttonGestionDepartamentos.setDisable(true);
            buttonGestionUsuarios.setDisable(true);
            buttonGestionEstadosTickets.setDisable(true);
            buttonGestionFlujosTrabajos.setDisable(true);
            buttonGestionTickets.setDisable(true);
            buttonUsuarioMisTickets.setDisable(true);
            buttonTecnicoTicketsPendientes.setDisable(true);
            buttonAdmiGestionTickets.setDisable(true);
            break;
    }
}*/
}