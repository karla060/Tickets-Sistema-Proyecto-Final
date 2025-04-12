/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Karlaa
 */
public class GestionUsuarioController implements Initializable {

    @FXML
    private TableView<Usuario> tblUsuario;
    @FXML
    private TableColumn<Usuario, String> tblNombreColumna;
    @FXML
    private TableColumn<Usuario, String> tblEmailColumna;
    @FXML
    private TableColumn<Usuario, String> tblNombreUsuarioColumna;
    @FXML
    private TableColumn<Usuario, String> tblContraseñaColumna;
    @FXML
    private TableColumn<Usuario, String> tblRolColumna;
    @FXML
    private TableColumn<Usuario, String> tblDepartamentoColumna;
    @FXML
    private Button buttonRegistrarUs;
    @FXML
    private Button buttonModificarUs;
    @FXML
    private Button buttonDesactivarUs;
    @FXML
    private Button buttonMenu;


    private ObservableList<Usuario> listaUsuarios;
    
    
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                // Inicializar la lista observable
        listaUsuarios = FXCollections.observableArrayList();

        // Configurar las columnas de la tabla
        tblNombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        tblEmailColumna.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblNombreUsuarioColumna.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        tblRolColumna.setCellValueFactory(new PropertyValueFactory<>("rol"));
        tblDepartamentoColumna.setCellValueFactory(new PropertyValueFactory<>("departamento"));

        // Vincular la lista observable a la tabla
        tblUsuario.setItems(listaUsuarios);     
      

        // Agregar usuarios de ejemplo
        listaUsuarios.add(new Usuario("Juan Pérez", "juan@gmail.com", "juan123", "Admin123!", "Administrador", ""));
        listaUsuarios.add(new Usuario("Ana Gómez", "ana@gmail.com", "ana456", "Tecni456!", "Técnico", ""));        
    } 
    
    public TableView<Usuario> getTblUsuario() {
        return tblUsuario;
    }
        
   public ObservableList<Usuario> getListaUsuarios() {
    return listaUsuarios;
   }
    
   public void setListaUsuarios(ObservableList<Usuario> listaUsuarios) {
    this.listaUsuarios = listaUsuarios;
    tblUsuario.setItems(listaUsuarios); // Asegura que la tabla se actualiza con la nueva referencia
}

   
    @FXML
private void handleRegistrarUs(ActionEvent event) {
    try {
        // Cargar la vista del formulario de registro
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionUsuario2Vista.fxml"));
        Parent root = loader.load();

           // Obtener el controlador del formulario
            GestionUsuario2Controller controller = loader.getController();

            // Pasar la lista de usuarios
            controller.setListaUsuarios(this.listaUsuarios);
        
         // Cargar los roles desde GestionRolesPermisos2Controller y GestionRolesPermisos2Vista
        FXMLLoader rolesLoader = new FXMLLoader(getClass().getResource("/vista/GestionRolesPermisos2Vista.fxml"));
        rolesLoader.load();
        GestionRolesPermisos2Controller rolesController = rolesLoader.getController();
        ObservableList<String> nombresRoles = rolesController.getNombresDeRoles();

        // Pasar los roles al ComboBox
        controller.setListaRoles(nombresRoles);

        // Habilitar departamentos solo si el rol seleccionado es "Técnico"
        controller.setDepartamentoSegunRol();
        
        // Limpiar los campos para un nuevo registro
        controller.limpiarCampos();
       

        // Crear un nuevo Stage para la nueva interfaz
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Registrar Nuevo Usuario");
        newStage.show();

        // Cerrar la ventana actual si es necesario
        Stage currentStage = (Stage) buttonRegistrarUs.getScene().getWindow();
        currentStage.close(); // Cierra la ventana actual
    } catch (IOException e) {
        // Mostrar un mensaje de error si no se puede cargar la vista
        mostrarMensajeError("Error al abrir el formulario: " + e.getMessage());
        e.printStackTrace(); // Imprimir detalles del error para depuración
    }
}

 


  @FXML
private void handleModificarUs(ActionEvent event) {
    try {
        // Seleccionar el usuario que se va a modificar
        Usuario seleccionado = tblUsuario.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarMensajeError("Debe seleccionar un usuario para modificar.");
            return;
        }

        // Cargar la vista del formulario
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionUsuario2Vista.fxml"));
        Parent root = loader.load();

        // Obtener el controlador del formulario
        GestionUsuario2Controller controller = loader.getController();

        // Pasar la lista de usuarios y configurar el usuario a editar
        controller.setListaUsuarios(this.listaUsuarios);
        controller.configurarModoModificar(seleccionado);
      
        
         // Cargar los roles desde GestionRolesPermisos2Controller y GestionRolesPermisos2Vista
        FXMLLoader rolesLoader = new FXMLLoader(getClass().getResource("/vista/GestionRolesPermisos2Vista.fxml"));
        rolesLoader.load();
        GestionRolesPermisos2Controller rolesController = rolesLoader.getController();
        ObservableList<String> nombresRoles = rolesController.getNombresDeRoles();

        // Pasar los roles al ComboBox
        controller.setListaRoles(nombresRoles);

         // Habilitar departamentos solo si el rol seleccionado es "Técnico"
        controller.setDepartamentoSegunRol();
        

        // Crear un nuevo Stage para la nueva interfaz
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Modificar Usuario");
        newStage.show();

        //cerrar la ventana actual
        Stage currentStage = (Stage) buttonModificarUs.getScene().getWindow();
        currentStage.close();
    } catch (IOException e) {
        mostrarMensajeError("Error al abrir el formulario: " + e.getMessage());
        e.printStackTrace();
    }
}
      private void registrarHistorialModificacion(Usuario usuario, String accion) {
    String mensajeHistorial = "Usuario: " + usuario.getNombreCompleto() + "\nAcción: " + accion + "\nFecha: " + java.time.LocalDateTime.now();
    // Aquí podrías guardar esta información en una base de datos o archivo de registros
    System.out.println("Historial: " + mensajeHistorial);
}
      
        private void mostrarMensajeInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }    

    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
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
 /*
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
    }*/

