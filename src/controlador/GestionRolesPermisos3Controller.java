/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 *
 * @author Karlaa
 */
public class GestionRolesPermisos3Controller implements Initializable {
 
    public static ObservableList<Permiso> permisos = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Permiso> tblPermisos;
    @FXML
    private TableColumn<Permiso, String> tblNombrePermisoColumna;
    @FXML
    private TableColumn<Permiso, String> tblDescripcionPermisoColumna;

    @FXML
    private TextField textNombrePermiso;
    @FXML
    private TextArea textDescripcionPermiso;
    @FXML
    private Button buttonGuardarPermiso;
    @FXML
    private Button buttonCancelarPermiso;
    @FXML
    private Button buttonRegresarPermiso;
    
    //private ObservableList<Permiso> permisos;

   @Override
public void initialize(URL url, ResourceBundle rb) {
    // Configurar la tabla
    tblPermisos.setItems(permisos);
    tblNombrePermisoColumna.setCellValueFactory(new PropertyValueFactory<>("nombrePermiso"));
    tblDescripcionPermisoColumna.setCellValueFactory(new PropertyValueFactory<>("descripcionPermiso"));

    // Añadir permisos iniciales (opcional para pruebas)
   inicializarPermisos();
}

public static void inicializarPermisos() {
        if (permisos.isEmpty()) {
            permisos.add(new Permiso("Crear tickets", ""));
            permisos.add(new Permiso("Ver tickets", ""));
            permisos.add(new Permiso("Editar tickets", ""));
            permisos.add(new Permiso("Eliminar tickets", ""));
            permisos.add(new Permiso("Asignar ticket", ""));
            permisos.add(new Permiso("Cambiar estado de tickets", ""));
            permisos.add(new Permiso("Gestionar usuarios", ""));
            permisos.add(new Permiso("Gestionar departamentos", ""));
            permisos.add(new Permiso("Gestionar flujos de trabajo", ""));
            permisos.add(new Permiso("Configurar parámetros del sistema", ""));
        }
}

   @FXML
   private void handleCrearPermiso(ActionEvent event) {
    String nombrePermiso = textNombrePermiso.getText();
    String descripcionPermiso = textDescripcionPermiso.getText();

    // Validar que el nombre del permiso tenga entre 3 y 50 caracteres
    if (nombrePermiso == null || nombrePermiso.length() < 3 || nombrePermiso.length() > 50) {
        mostrarMensajeError("El nombre del permiso debe contener entre 3 y 50 caracteres.");
        return;
    }

    // Crear el nuevo permiso y añadirlo a la lista global de permisos
    Permiso nuevoPermiso = new Permiso(nombrePermiso, descripcionPermiso);
    GestionRolesPermisos3Controller.permisos.add(nuevoPermiso);

    // Limpiar campos y mostrar un mensaje de éxito
    limpiarCamposPermiso();
    //mostrarMensajeInfo("El nuevo permiso ha sido creado exitosamente.");
}


    /*@FXML
    private void handleGuardarPermiso(ActionEvent event) {
        // Crear un nuevo permiso basado en los datos ingresados
        String nombrePermiso = textNombrePermiso.getText();
        String descripcionPermiso = textDescripcionPermiso.getText();

        if (nombrePermiso.isEmpty() || descripcionPermiso.isEmpty()) {
            mostrarMensajeError("El nombre y la descripción del permiso no pueden estar vacíos.");
            return;
        }

        Permiso nuevoPermiso = new Permiso(nombrePermiso, descripcionPermiso);
        permisos.add(nuevoPermiso);
        limpiarCamposPermiso();
    }*/
    

    

    private void limpiarCamposPermiso() {
        textNombrePermiso.clear();
        textDescripcionPermiso.clear();  
        
    }

    
    @FXML
    private void handleCancelarPermiso(ActionEvent event) {
    // Limpiar todos los campos
    limpiarCamposPermiso();
    
    // Opcional: mostrar un mensaje al usuario para confirmar la cancelación
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Ingreso Cancelado");
    alert.setHeaderText(null);
    alert.setContentText("El ingreso del permiso ha sido cancelado.");
    alert.showAndWait();   
}
    
    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
 @FXML 
     private void handleRegresar (ActionEvent event) {
            
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
    
}