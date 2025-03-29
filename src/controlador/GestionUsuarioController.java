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
public class GestionUsuarioController implements Initializable {

     @FXML
    private TableView<?> tblUsuario;
    @FXML
    private TableColumn<?, String> tblNombreColumna;
    @FXML
    private TableColumn<?, String> tblEmailColumna;
    @FXML
    private TableColumn<?, String> tblNombreUsuarioColumna;
    @FXML
    private TableColumn<?, String> tblContraseñaColumna;
    @FXML
    private TableColumn<?, String> tblRolColumna;
    @FXML
    private TableColumn<?, String> tblDepartamentoColumna;
     @FXML
    private Button buttonRegistrarUs;
    @FXML
    private Button buttonModificarUs;
    @FXML
    private Button buttonDesactivarUs;
    @FXML
    private TextField textNombreCompleto;
    @FXML
    private TextField textCorreoElectronico;
    @FXML
    private TextField textNombreUsuario;
    @FXML
    private PasswordField passContrasena;
    @FXML
    private ComboBox<String> comboBoxRol;
    @FXML
    private ComboBox<String> comboBoxDepartamento;
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonRegresar;
   /* @FXML
    private Button buttonConfirmar;
    @FXML
    private Button buttonCancelar2;*/
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        
        // TODO
    }    
    
     @FXML
    private void handleVentanaUsuario2(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionUsuario2Vista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionUsuarioController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }  
    
     @FXML 
     private void handleVentana (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionUsuarioVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionUsuarioController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
             java.util.logging.Logger.getLogger(GestionUsuarioController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }
    
    
    
}
