/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Karlaa
 */
public class GestionTicketController implements Initializable {
     @FXML
    private TableView<?> tblTickets;
    @FXML
    private TableColumn<?, String> tblTituloColumna;
    @FXML
    private TableColumn<?, String> tblDescripcionColumna;
    @FXML
    private TableColumn<?, String> tblDepartamentoColumna;
    @FXML
    private TableColumn<?, String> tblPrioridadColumna;
  /*@FXML
    private TableColumn<?, String> tblEstadoColumna;
    @FXML
    private TableColumn<?, String> tblAccionesColumna;*/
    @FXML
    private Button buttonCrearTicket;
    @FXML
    private Button buttonModificarTicket;
    @FXML
    private Button buttonAsignarTicket;
    @FXML
    private Button buttonCerrarTicket;
    @FXML
    private TextField textTituloTicket;
    @FXML
    private TextArea textDescripcionTicket;
    @FXML
    private ComboBox<String> comboBoxDepartamentoTicket;
    @FXML
    private Button buttonGuardaar;
    @FXML
    private Button buttonCancelaar;
    @FXML
    private ComboBox<String> comboBoxPrioridadTicket;
    /*@FXML
    private FileChooser fileChooserAdjuntos;*/
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;
     @FXML
    private Button buttonRegresar;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void handleVentana2(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionTicket2Vista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionTicketController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
             java.util.logging.Logger.getLogger(GestionTicketController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }  
       
    
     @FXML 
     private void handleVentana(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionTicketVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionTicketController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
             java.util.logging.Logger.getLogger(GestionTicketController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }     
    
    
    }
    
    
}
