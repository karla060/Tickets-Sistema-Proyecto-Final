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
public class GestionFlujosTrabajoController implements Initializable {

    @FXML
    private TableView<?> tblFlujosTrabajo;
    @FXML
    private TableColumn<?, String> tblNombreFlujoColumna;
    @FXML
    private TableColumn<?, String> tblEstadosInvolucradosColumna;
    @FXML
    private TableColumn<?, String> tblTransicionesPermitidas;
    @FXML
    private TableColumn<?, String> tblReglasTransicion;
    @FXML
    private TableColumn<?, String> tblAccionesColumna;
    @FXML
    private Button buttonCrearFlujo;
    @FXML
    private Button buttonModificarFlujo;
    @FXML
    private Button buttonEliminarFlujo;
    @FXML
    private TextField textNombreFlujo;
    @FXML
    private ListView<CheckBox> listEstadosInvolucrados;
    @FXML
    private ListView<CheckBox> listTransicionesPermitidas;
    @FXML
    private TextArea textReglasTransicion;
    @FXML
    private TextArea textAccionesAutomaticas;
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
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionFlujosTrabajo2Vista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionFlujosTrabajoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }   
    @FXML 
    private void handleVentana(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionFlujosTrabajoVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionFlujosTrabajoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
             java.util.logging.Logger.getLogger(GestionFlujosTrabajoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }     
    
    
    }
    
    
}
