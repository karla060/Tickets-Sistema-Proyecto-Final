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

import modelo.Departamento;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Usuario;
/**
 * FXML Controller class
 *
 * @author Karlaa
 */


public class GestionDepartamentosController implements Initializable {

    @FXML
    private TableView<Departamento> tblDepartmento;
    @FXML
    private TableColumn<Departamento, String> tblNombreColumna;
    @FXML
    private TableColumn<Departamento, String> tblDescripcionColumna;
    @FXML
    private TableColumn<Departamento, String> tblTecnicosAsigandosColumna;

    @FXML
    private Button buttonCrearDepa;
    @FXML
    private Button buttonModificarDepa;
    @FXML
    private Button buttonEliminarDepa;
    @FXML
    private Button buttonMenu;

    // Lista de departamentos
    private ObservableList<Departamento> departamentos;  
     /**
     * Initializes the controller class.
     */
    
    
   @Override
   public void initialize(URL url, ResourceBundle rb) {

        // Inicializar la lista observable y configurar la tabla
        departamentos = FXCollections.observableArrayList();
        tblDepartmento.setItems(departamentos);

        // Configurar columnas para mostrar datos de los departamentos
        tblNombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblDescripcionColumna.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tblTecnicosAsigandosColumna.setCellValueFactory(new PropertyValueFactory<>("tecnicosAsignados"));

        // Ejemplo de departamentos iniciales
        departamentos.add(new Departamento("TI", "Soporte técnico", "Juan, Ana"));
        departamentos.add(new Departamento("Recursos Humanos", "Gestión de personal", "Carlos, Luisa"));
    }

   public void setListaDepartamentos(ObservableList<Departamento> listaDepartamentos) {
    this.departamentos = listaDepartamentos;
    tblDepartmento.setItems(listaDepartamentos); // Enlazar la tabla con la lista actualizada
}
   public ObservableList<String> getNombresDeDepartamentos() {
    ObservableList<String> nombresDepartamentos = FXCollections.observableArrayList();
    departamentos.forEach(departamento -> nombresDepartamentos.add(departamento.getNombre()));
    return nombresDepartamentos;
}

   
   @FXML
private void handleCrearDepa(ActionEvent event) {
    try {
        // Cargar la vista del formulario de creación/modificación
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionDepartamento2Vista.fxml"));
        Parent root = loader.load();

        // Obtener el controlador del formulario
        GestionDepartamentos2Controller controller = loader.getController();

        // Pasar la lista de departamentos al formulario para que pueda actualizarla
        controller.setListaDepartamentos(departamentos);

        // NO hay departamento en edición; el formulario estará limpio
        controller.limpiarCampos();

        
        // Crear un nuevo Stage para la nueva interfaz
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Crear Nuevo Departamento");
        newStage.show();

        // Cerrar la ventana actual
        Stage currentStage = (Stage) buttonCrearDepa.getScene().getWindow();
        currentStage.close(); // Cierra la ventana actual
    } catch (IOException e) {
        // Mostrar un mensaje de error si no se puede cargar la vista
        mostrarMensajeError("Error al abrir el formulario: " + e.getMessage());
        e.printStackTrace(); // Imprimir detalles del error para depuración
    }
}


    @FXML
private void handleModificarDepa(ActionEvent event) {
    try {
        Departamento seleccionado = tblDepartmento.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarMensajeError("Debe seleccionar un departamento para modificar.");
            return;
        }

        // Cargar la vista del formulario
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionDepartamento2Vista.fxml"));
        Parent root = loader.load();

        // Obtener el controlador del formulario y configurar el modo modificación
        GestionDepartamentos2Controller controller = loader.getController();
        controller.setListaDepartamentos(departamentos);
        controller.configurarModoModificar(seleccionado);

        // Crear un nuevo Stage para la nueva interfaz
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Modificar Departamento");
        newStage.show();

        // Cerrar la ventana actual
        Stage currentStage = (Stage) buttonModificarDepa.getScene().getWindow();
        currentStage.close(); // Cierra la ventana actual
             
    } catch (Exception e) {
        mostrarMensajeError("Error al abrir el formulario: " + e.getMessage());
        e.printStackTrace(); // Depurar el error
    }
}


/*  try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionDepartamento2Vista.fxml"));
             Parent root;

             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionDepartamentosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  */

   

    @FXML
    private void handleEliminarDepa(ActionEvent event) {
        Departamento seleccionado = tblDepartmento.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarMensajeError("Debe seleccionar un departamento para eliminar.");
            return;
        }

        if (seleccionado.tieneTicketsActivos()) {
            mostrarMensajeError("No puede eliminar un departamento con tickets activos.");
            return;
        }

        departamentos.remove(seleccionado);
        mostrarMensajeInfo("Departamento eliminado exitosamente.");
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
             java.util.logging.Logger.getLogger(GestionDepartamentosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

    void setListaUsuarios(ObservableList<Usuario> listaUsuarios) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
   

    /*@FXML
    private void handleVentana2(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionDepartamento2Vista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionDepartamentosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }
    
     @FXML
    private void handleVentana(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionDepartamentosVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionDepartamentosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
             java.util.logging.Logger.getLogger(GestionDepartamentosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }
*/    

