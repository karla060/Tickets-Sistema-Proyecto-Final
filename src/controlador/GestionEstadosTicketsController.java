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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.EstadoTicket;

/**
 * FXML Controller class
 *
 * @author Karlaa
 */
public class GestionEstadosTicketsController implements Initializable {

    @FXML
    private TableView<EstadoTicket> tblEstados;
    @FXML
    private TableColumn<EstadoTicket, String> tblNombreEstadoColumna;
    @FXML
    private TableColumn<EstadoTicket, String> tblDescripcionEstadoColumna;
    @FXML
    private TableColumn<EstadoTicket, String> tblEstadoFinalColumna;
    @FXML
    private TableColumn<EstadoTicket, String> tblEstadosSiguientesColumna;
    @FXML
    private Button buttonCrearEstado;
    @FXML
    private Button buttonModificarEstado;
    @FXML
    private Button buttonEliminarEstado;
    @FXML
    private Button buttonMenu;

    private ObservableList<EstadoTicket> listaEstados;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar la lista observable
        listaEstados = FXCollections.observableArrayList();
          // Vincular la lista observable a la tabla
        tblEstados.setItems(listaEstados);

        // Configurar las columnas para mostrar los estados
        tblNombreEstadoColumna.setCellValueFactory(new PropertyValueFactory<>("nombreEstado"));
        tblDescripcionEstadoColumna.setCellValueFactory(new PropertyValueFactory<>("descripcionEstado"));
        tblEstadoFinalColumna.setCellValueFactory(new PropertyValueFactory<>("estadoFinal"));
        tblEstadosSiguientesColumna.setCellValueFactory(new PropertyValueFactory<>("estadosSiguientes"));

      

        // Ejemplo de datos iniciales
        listaEstados.add(new EstadoTicket("Resuelto", "Ticket solucionado", "Sí", "N/A"));
        listaEstados.add(new EstadoTicket("En Proceso", "", "", ""));
        listaEstados.add(new EstadoTicket("Pendiente", "En espera de resolución", "No", "Resuelto, Cerrado"));
    }

    @FXML
    private void handleCrearEstado(ActionEvent event) {
        try {
            // Cargar la vista del formulario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionEstadosTickets2Vista.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del formulario
            GestionEstadosTickets2Controller controller = loader.getController();

            // Pasar la lista de estados al formulario
            controller.setListaEstados(listaEstados);

            controller.limpiarCampos();
            
            // Crear una nueva ventana para el formulario
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Crear Nuevo Estado");
            stage.show();
            
           
             //cerrar la ventana
            Stage currentStage = (Stage) buttonCrearEstado.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            mostrarMensajeError("Error al abrir el formulario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleModificarEstado(ActionEvent event) {
        // Verificar que se haya seleccionado un estado
        EstadoTicket seleccionado = tblEstados.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarMensajeError("Debe seleccionar un estado para modificar.");
            return;
        }

        try {
            // Cargar la vista del formulario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionEstadosTickets2Vista.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del formulario
            GestionEstadosTickets2Controller controller = loader.getController();

            // Pasar la lista y el estado seleccionado al formulario
            controller.setListaEstados(listaEstados);
            controller.configurarModoModificar(seleccionado);  
            
            // Crear una nueva ventana para el formulario
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Modificar Estado");
            stage.show();
            
            //cerrar la ventana
            Stage currentStage = (Stage) buttonModificarEstado.getScene().getWindow();
            currentStage.close(); 
        } catch (IOException e) {
            mostrarMensajeError("Error al abrir el formulario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEliminarEstado(ActionEvent event) {
        EstadoTicket seleccionado = tblEstados.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarMensajeError("Debe seleccionar un estado para eliminar.");
            return;
        }

        listaEstados.remove(seleccionado);
         mostrarMensajeInfo("Estado eliminado exitosamente.");
    }

    
    private void mostrarMensajeInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
     
    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

        public void setListaEstados(ObservableList<EstadoTicket> listaEstados) {
        // Actualizar la lista observable en el controlador
          this.listaEstados = listaEstados;

        // Vincular la lista actualizada al TableView
        tblEstados.setItems(this.listaEstados);
  }
        public ObservableList<EstadoTicket> getListaEstados() {
    return listaEstados;
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


/*    
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
    
    }*/
    
    
    
    
    
    
    
    

