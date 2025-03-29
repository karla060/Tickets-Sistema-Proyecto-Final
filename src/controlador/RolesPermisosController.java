/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author Karlaa
 */

  /*
textNombreRol
textDescripcionRol
buttonGuardarRol
buttonEditarRol
buttonCancelarProceso
listPermisosDisponibles
buttonAgregarRol
textNombrePermiso
textDescripcionPermiso

buttonAgregarPermiso
listRolesDisponibles
buttonEliminarRol

buttonAtras

*/

public class RolesPermisosController implements Initializable {
    @FXML
    private TextField textNombreRol;
    @FXML
    private TextArea textDescripcionRol;
    @FXML
    private ListView<CheckBox> listPermisosDisponibles;
    @FXML
    private ListView<String> listRolesDisponibles;
    @FXML
    private Button buttonAgregarPermiso;
    @FXML
    private Button buttonCancelarProceso;
    @FXML
    private Button buttonEliminarRol;
    @FXML
    private Button buttonGuardarRol;
    @FXML
    private TextField textNombrePermiso;
    @FXML
    private TextArea textDescripcionPermiso;
    @FXML
    private Button buttonAgregarRol;
    @FXML
    private Button buttonEditarRol;
    @FXML
    private String rolSeleccionado;
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

          // Inicializar roles existentes (hasta el momento)
        listRolesDisponibles.getItems().addAll("Administrador", "Técnico", "Usuario");

        // Inicializar permisos existentes (hasta el momento)
        listPermisosDisponibles.getItems().addAll(
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
                
        );
           
    }    
    
    //Regresar Al menú principal
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
             java.util.logging.Logger.getLogger(RolesPermisosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
         
    
     }
}