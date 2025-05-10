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
import modelo.PermisoDAO;
import modelo.RolDAO;
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
    public void initialize(URL url, java.util.ResourceBundle rb) {
        // Cargar la lista de roles desde la BD mediante el DAO
        RolDAO rolDAO = new RolDAO();
        List<Rol> listaRolBD = rolDAO.obtenerRoles();
        roles = FXCollections.observableArrayList(listaRolBD);
        tblRoles.setItems(roles);
     
        // Configurar las columnas del TableView
        tblNombreRolColumna.setCellValueFactory(new PropertyValueFactory<>("nombreRol"));
        tblDescripcionRolColumna.setCellValueFactory(new PropertyValueFactory<>("descripcionRol"));
        tblPermisosRolColumna.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getPermisosAsignados())
        );
        
        // Configurar el ListView de permisos obteniendo los permisos guardados en la BD.
        configurarListPermisosAsignados();
    }

    /**
     * Configura el ListView para que se muestren los permisos ya almacenados en la BD.
     * Se usa el PermisoDAO para obtener los permisos y se agrega un CheckBox por cada permiso.
     */
    
    private void configurarListPermisosAsignados() {
        listPermisosAsignados.getItems().clear(); // Limpiar cualquier elemento previo
        
        // Obtener la lista de permisos desde la base de datos
        PermisoDAO permisoDAO = new PermisoDAO();
        List<Permiso> permisosBD = permisoDAO.obtenerPermisos();
        
        // Por cada permiso obtenido, se crea un CheckBox y se añade al ListView.
        for (Permiso permiso : permisosBD) {
            listPermisosAsignados.getItems().add(new CheckBox(permiso.getNombrePermiso()));
        }
    }
    
    public ObservableList<String> getNombresDeRoles() {
        ObservableList<String> nombresRoles = FXCollections.observableArrayList();
        roles.forEach(rol -> nombresRoles.add(rol.getNombreRol()));
        return nombresRoles;
    }
    
    
    private List<Permiso> obtenerPermisosSeleccionados() {
    // Se crea un ArrayList para almacenar los permisos seleccionados.
    ArrayList<Permiso> permisosAsignados = new ArrayList<>();
    PermisoDAO permisoDAO = new PermisoDAO();

    // Se recorre cada CheckBox contenido en el ListView de permisos.
    for (CheckBox checkBox : listPermisosAsignados.getItems()) {
        // Si el CheckBox está seleccionado...
        if (checkBox.isSelected()) {
            // Crear el objeto Permiso con el nombre del CheckBox.
            Permiso p = new Permiso(checkBox.getText(), "");
            // Se consulta el id del permiso en la base de datos usando el DAO.
            int id = permisoDAO.obtenerIdPermisoPorNombre(checkBox.getText());
            p.setIdPermiso(id);
            // Se agrega el permiso al ArrayList.
            permisosAsignados.add(p);
        }
    }
    return permisosAsignados;
}

    
    @FXML
    private void handleCrearRol(ActionEvent event) {
        String nombreRol = textNombreRol.getText();
        String descripcionRol = textDescripcionRol.getText();

        if (nombreRol == null || nombreRol.trim().length() < 3 || nombreRol.trim().length() > 50) {
            mostrarMensajeError("El nombre del rol debe contener entre 3 y 50 caracteres.");
            return;
        }

        // Obtener los permisos seleccionados desde el ListView
        List<Permiso> permisosAsignados = listPermisosAsignados.getItems().stream()
            .filter(CheckBox::isSelected)
            .map(checkBox -> new Permiso(checkBox.getText(), "")) // Aquí, si hay más datos de permiso, actualízalo.
            .collect(Collectors.toList());

        // Crear el nuevo rol y agregarlo a la base de datos y a la lista observable
        Rol nuevoRol = new Rol(nombreRol.trim(), descripcionRol.trim(), permisosAsignados);
        RolDAO rolDAO = new RolDAO();
        rolDAO.insertarRol(nuevoRol);
        
        roles.add(nuevoRol);
        limpiarCamposRol();
    }
    
    @FXML
    private void handleModificarRol(ActionEvent event) {
        Rol rolSeleccionado = tblRoles.getSelectionModel().getSelectedItem();
        if (rolSeleccionado == null) {
            mostrarMensajeError("Debe seleccionar un rol para modificar.");
            return;
        }

        String nombreRol = textNombreRol.getText();
        String descripcionRol = textDescripcionRol.getText();

        if (nombreRol == null || nombreRol.trim().length() < 3 || nombreRol.trim().length() > 50) {
            mostrarMensajeError("El nombre del rol debe contener entre 3 y 50 caracteres.");
            return;
        }

        rolSeleccionado.setNombreRol(nombreRol.trim());
        rolSeleccionado.setDescripcionRol(descripcionRol.trim());

        List<Permiso> permisosAsignados = listPermisosAsignados.getItems().stream()
            .filter(CheckBox::isSelected)
            .map(checkBox -> new Permiso(checkBox.getText(), ""))
            .collect(Collectors.toList());
        rolSeleccionado.setPermisos(permisosAsignados);

        RolDAO rolDAO = new RolDAO();
        rolDAO.actualizarRol(rolSeleccionado);
        tblRoles.refresh();

        limpiarCamposRol();
    }
    
    @FXML
    private void handleEliminarRol(ActionEvent event) {
        Rol rolSeleccionado = tblRoles.getSelectionModel().getSelectedItem();
        if (rolSeleccionado == null) {
            mostrarMensajeError("Debe seleccionar un rol para eliminar.");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Está seguro de que desea eliminar el rol seleccionado?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            RolDAO rolDAO = new RolDAO();
            rolDAO.eliminarRol(rolSeleccionado.getIdRol());
            roles.remove(rolSeleccionado);
            mostrarMensajeInfo("El rol ha sido eliminado correctamente.");
        }
    }
    
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
    
    private void limpiarCamposRol() {
        textNombreRol.clear();
        textDescripcionRol.clear();
        listPermisosAsignados.getItems().forEach(checkBox -> checkBox.setSelected(false));
    }
    
    @FXML
    private void handleCancelarRol(ActionEvent event) {
        limpiarCamposRol();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ingreso Cancelado");
        alert.setHeaderText(null);
        alert.setContentText("El ingreso del rol ha sido cancelado.");
        alert.showAndWait();
    }
    
    @FXML 
    private void handleRegresar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionRolesPermisosVista.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(GestionRolesPermisos2Controller.class.getName())
                .log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
    
