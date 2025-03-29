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
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Karlaa
 */
public class GestionRolesPermisosController implements Initializable {
    
    @FXML
    private TableView<?> tblRoles;
    @FXML
    private TableColumn<?, String> tblNombreRolColumna;
    @FXML
    private TableColumn<?, String> tblDescripcionRolColumna;
   /* @FXML
    private TableColumn<?, String> tblAccionesRolColumna;*/
    @FXML
    private TableView<?> tblPermisos;
    @FXML
    private TableColumn<?, String> tblNombrePermisoColumna;
    @FXML
    private TableColumn<?, String> tblDescripcionPermisoColumna;
    /*@FXML
    private TableColumn<?, String> tblAccionesPermisoColumna;*/
    @FXML
    private Button buttonCrearRol;
    @FXML
    private Button buttonModificarRol;
    @FXML
    private Button buttonCrearPermiso;
    @FXML
    private Button buttonEliminarRol;
    @FXML
    private Button buttonRegresar;
    
    @FXML
    private TextField textNombreRol;
    @FXML
    private TextArea textDescripcionRol;
    @FXML
    private ListView<CheckBox> listPermisosAsignados;
    @FXML
    private Button buttonGuardarRol;
    @FXML
    private Button buttonCancelarRol;
    
    @FXML
    private TextField textNombrePermiso;
    @FXML
    private TextArea textDescripcionPermiso;
    @FXML
    private Button buttonGuardarPermiso;
    @FXML
    private Button buttonCancelarPermiso;
   
    /**
     * Initializes the controller class.
     */
    @Override
     public void initialize(URL url, ResourceBundle rb) {
     /*   listPermisosAsignados.getItems().addAll(
        new CheckBox("Crear tickets"),
        new CheckBox("Ver tickets"),
        new CheckBox("Editar tickets"),
        new CheckBox("Eliminar tickets"),
        new CheckBox("Asignar tickets"),
        new CheckBox("Cambiar estado de tickets"),
        new CheckBox("Agregar notas a tickets"),
        new CheckBox("Gestionar usuarios"),
        new CheckBox("Gestionar departamentos"),
        new CheckBox("Gestionar flujos de trabajo"),
        new CheckBox("Configurar parámetros del sistema")
        );*/
        // TODO
    }    
     @FXML 
     private void handleVentana3(ActionEvent event) {
            
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
     private void handleVentana2 (ActionEvent event) {
            
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
    
}
