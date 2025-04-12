/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

import javafx.collections.ListChangeListener;

import modelo.Rol;
import modelo.Permiso;
/**
 *
 * @author Karlaa
 */
public class GestionRolesPermisos2Controller implements Initializable {

    @FXML
    private TableView<Rol> tblRoles;
    @FXML
    private TableColumn<Rol, String> tblNombreRolColumna;
    @FXML
    private TableColumn<Rol, String> tblDescripcionRolColumna;
    @FXML
    private TableColumn<Rol, String> tblPermisosRolColumna;

    @FXML
    private Button buttonCrearRol;
    @FXML
    private Button buttonModificarRol;
    @FXML
    private Button buttonEliminarRol;
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
    private Button buttonRegresar;
     
    
    private ObservableList<Rol> roles;
    
   /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     // Inicializar la lista de roles
    roles = FXCollections.observableArrayList();
    tblRoles.setItems(roles);
    // Configurar las columnas de la tabla
    tblNombreRolColumna.setCellValueFactory(new PropertyValueFactory<>("nombreRol"));
    tblDescripcionRolColumna.setCellValueFactory(new PropertyValueFactory<>("descripcionRol"));
    tblPermisosRolColumna.setCellValueFactory(data -> 
    new javafx.beans.property.SimpleStringProperty(data.getValue().getPermisosAsignados())
);
   /* tblNombreRolColumna.setCellValueFactory(new PropertyValueFactory<>("nombreRol"));
    tblDescripcionRolColumna.setCellValueFactory(new PropertyValueFactory<>("descripcionRol"));
    tblPermisosRolColumna.setCellValueFactory(new PropertyValueFactory<>("permisosAsignados"));*/

    // Agregar roles iniciales

     roles.add(new Rol("Administrador", "Acceso completo al sistema", new ArrayList<>()));
     roles.add(new Rol("Técnico", "Realiza soporte técnico", new ArrayList<>()));
     roles.add(new Rol("Usuario", "Acceso limitado", new ArrayList<>()));
   
    // Inicializar permisos si aún no están cargados
    GestionRolesPermisos3Controller.inicializarPermisos();

    // Configurar permisos iniciales en el ListView
    configurarListPermisosAsignados();
}

    private void configurarListPermisosAsignados() {
    listPermisosAsignados.getItems().clear(); // Limpiar permisos previos
    GestionRolesPermisos3Controller.permisos.forEach(permiso -> 
        listPermisosAsignados.getItems().add(new CheckBox(permiso.getNombrePermiso()))
    );
}
  
    
     // Método para obtener los nombres de los roles como cadena
 /*  public ObservableList<String> getNombresDeRoles() {
    if (roles == null) {
        roles = FXCollections.observableArrayList(); // Inicializar si está null
     }
    ObservableList<String> nombresRoles = FXCollections.observableArrayList();
    // Extraer los nombres de los roles
    roles.forEach(rol -> nombresRoles.add(rol.getNombreRol())); 
    return nombresRoles;
}*/
   
   public ObservableList<String> getNombresDeRoles() {
    ObservableList<String> nombresRoles = FXCollections.observableArrayList();
    roles.forEach(rol -> nombresRoles.add(rol.getNombreRol()));
    return nombresRoles;
}

    @FXML
   private void handleCrearRol(ActionEvent event) {
    String nombreRol = textNombreRol.getText();
    String descripcionRol = textDescripcionRol.getText();

    // Validar que el nombre tenga entre 3 y 50 caracteres
    if (nombreRol == null || nombreRol.length() < 3 || nombreRol.length() > 50) {
        mostrarMensajeError("El nombre del rol debe contener entre 3 y 50 caracteres.");
        return;
    }

    // Obtener los permisos seleccionados
    List<Permiso> permisosAsignados = listPermisosAsignados.getItems().stream()
        .filter(CheckBox::isSelected)
        .map(checkBox -> new Permiso(checkBox.getText(), ""))
        .collect(Collectors.toList());

    // Crear el nuevo rol y agregarlo a la lista
    Rol nuevoRol = new Rol(nombreRol, descripcionRol, permisosAsignados);
    roles.add(nuevoRol);

    // Limpiar los campos y mostrar un mensaje de éxito
    limpiarCamposRol();
   // mostrarMensajeInfo("El nuevo rol ha sido creado exitosamente.");
   }
    
   /* @FXML
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
}*/
   
    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private void mostrarMensajeInfo(String mensaje) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Información");
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    alert.showAndWait();
}

    @FXML
private void handleModificarRol(ActionEvent event) {
    Rol rolSeleccionado = tblRoles.getSelectionModel().getSelectedItem();

    // Verificar si se ha seleccionado un rol
    if (rolSeleccionado == null) {
        mostrarMensajeError("Debe seleccionar un rol para modificar.");
        return;
    }

    String nombreRol = textNombreRol.getText();
    String descripcionRol = textDescripcionRol.getText();

    // Validar que el nombre tenga entre 3 y 50 caracteres
    if (nombreRol == null || nombreRol.length() < 3 || nombreRol.length() > 50) {
        mostrarMensajeError("El nombre del rol debe contener entre 3 y 50 caracteres.");
        return;
    }

    // Actualizar los datos del rol seleccionado
    rolSeleccionado.setNombreRol(nombreRol);
    rolSeleccionado.setDescripcionRol(descripcionRol);

    // Obtener los permisos seleccionados
    List<Permiso> permisosAsignados = listPermisosAsignados.getItems().stream()
        .filter(CheckBox::isSelected)
        .map(checkBox -> new Permiso(checkBox.getText(), ""))
        .collect(Collectors.toList());
    rolSeleccionado.setPermisos(permisosAsignados);

    // Refrescar la tabla para mostrar los cambios
    tblRoles.refresh();

    // Limpiar los campos y mostrar un mensaje de éxito
    limpiarCamposRol();
   // mostrarMensajeInfo("Los cambios en el rol han sido guardados correctamente.");
}

    
    

   @FXML
private void handleEliminarRol(ActionEvent event) {
    Rol rolSeleccionado = tblRoles.getSelectionModel().getSelectedItem();

    // Verificar si se ha seleccionado un rol
    if (rolSeleccionado == null) {
        mostrarMensajeError("Debe seleccionar un rol para eliminar.");
        return;
    }


    // Confirmar eliminación del rol
    Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
    confirmacion.setTitle("Confirmar Eliminación");
    confirmacion.setHeaderText(null);
    confirmacion.setContentText("¿Está seguro de que desea eliminar el rol seleccionado?");
    
    Optional<ButtonType> resultado = confirmacion.showAndWait();
    if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
        roles.remove(rolSeleccionado);
        mostrarMensajeInfo("El rol ha sido eliminado correctamente.");
    }
}
   private void mostrarMensajeAdvertencia(String mensaje) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Advertencia");
    alert.setHeaderText(null);
    alert.setContentText(mensaje);
    alert.showAndWait();
}

    private void limpiarCamposRol() {
        textNombreRol.clear();
        textDescripcionRol.clear();
        listPermisosAsignados.getItems().forEach(checkBox -> checkBox.setSelected(false));
    }

   
    
    @FXML
    private void handleCancelarRol(ActionEvent event) {
    // Limpiar todos los campos
    limpiarCamposRol();
    
    // Opcional: mostrar un mensaje al usuario para confirmar la cancelación
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Ingreso Cancelado");
    alert.setHeaderText(null);
    alert.setContentText("El ingreso del rol ha sido cancelado.");
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
