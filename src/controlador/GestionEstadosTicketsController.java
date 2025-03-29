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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Karlaa
 */
public class GestionEstadosTicketsController implements Initializable {

     @FXML
    private TableView<?> tblEstados;
    @FXML
    private TableColumn<?, String> tblNombreEstadoColumna;
    @FXML
    private TableColumn<?, String> tblDescripcionEstadoColumna;
    @FXML
    private TableColumn<?, String> tblEstadoFinalColumna;
    @FXML
    private TableColumn<?, String> tblEstadosSiguientesColumna;
    @FXML
    private Button buttonCrearEstado;
    @FXML
    private Button buttonModificarEstado;
    @FXML
    private Button buttonEliminarEstado;
    @FXML
    private TextField textNombreEstado;
    @FXML
    private TextArea textDescripcionEstado;
    @FXML
    private ListView<CheckBox> listEstadoFinal;
    @FXML
    private ListView<CheckBox> listEstadosSiguientes;
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
       /* listEstadoFinal.getItems().addAll(
            new CheckBox("Resuelto"),
            new CheckBox("No Resuelto")
            );*/
        
    }    
     @FXML
    private void handleVentana2(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionEstadosTickets2Vista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionEstadosTicketsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }  
    
     @FXML 
     private void handleVentana(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionEstadosTicketsVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionEstadosTicketsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
             java.util.logging.Logger.getLogger(GestionEstadosTicketsController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }
    
    
    
    
    
    
    
    
}
