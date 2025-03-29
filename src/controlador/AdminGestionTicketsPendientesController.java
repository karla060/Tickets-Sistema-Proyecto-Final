/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author mpelv
 */
public class AdminGestionTicketsPendientesController implements Initializable {
    @FXML
    private TableView<?> tblAdminTicketsPendientes;
    @FXML
    private TableColumn<?, String> tblNumeroTickestColumna;
    @FXML
    private TableColumn<?, String> tblEstadoTicketsColumna;
    @FXML
    private TableColumn<?, LocalDate> tblFechaCreacionColumna; 
    @FXML
    private TableColumn<?, String> tblDepartamentoColumna;
    @FXML
    private TableColumn<?, String> tblPrioridadColumna;
    @FXML
    private TableColumn<?, String> tblResumenProblemaColumna;
    @FXML
    private TableColumn<?, String>  tblNotaColumna;
    @FXML
    private Button buttonAplicarFiltros;
    @FXML
    private Button buttonAgregarNota;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonCambiarEstado;  
    @FXML
    private ComboBox comboBoxPrioridad; 
    @FXML
    private ComboBox comboBoxEstado; 
     
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
       @FXML 
        private void handleCambioEstadoTicket(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/CambioEstadoTicketVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(AdminGestionTicketsPendientesController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
             java.util.logging.Logger.getLogger(AdminGestionTicketsPendientesController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }
    
    @FXML
    private void handleAgregarNota(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/AgregarNotaTicketVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(AdminGestionTicketsPendientesController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }  
    
     @FXML 
     private void handleVentana3(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionTicket3Vista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(AdminGestionTicketsPendientesController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }  
    
    
    
}
