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
import modelo.FlujoTrabajo;



/**
 * 
 * @author Karlaa
 */
public class GestionFlujosTrabajoController implements Initializable {

    @FXML
    private TableView<FlujoTrabajo> tblFlujosTrabajo;
    @FXML
    private TableColumn<FlujoTrabajo, String> tblNombreFlujoColumna;
    @FXML
    private TableColumn<FlujoTrabajo, String> tblEstadosInvolucradosColumna;
    @FXML
    private TableColumn<FlujoTrabajo, String> tblTransicionesPermitidas;
    @FXML
    private TableColumn<FlujoTrabajo, String> tblReglasDeTransicion; // Modificado
    @FXML
    private TableColumn<FlujoTrabajo, String> tblAccionesColumna;
    @FXML
    private Button buttonCrearFlujo;
    @FXML
    private Button buttonModificarFlujo;
    @FXML
    private Button buttonEliminarFlujo;
    @FXML
    private Button buttonMenu;

    private ObservableList<FlujoTrabajo> listaFlujos;


     /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar la lista observable
        listaFlujos = FXCollections.observableArrayList();
      
        // Vincular la lista observable a la tabla
        tblFlujosTrabajo.setItems(listaFlujos);

        
        
        // Configurar las columnas para los datos de los Flujos de Trabajo
        tblNombreFlujoColumna.setCellValueFactory(new PropertyValueFactory<>("nombreFlujo"));
        tblEstadosInvolucradosColumna.setCellValueFactory(new PropertyValueFactory<>("estadosInvolucrados"));
        tblTransicionesPermitidas.setCellValueFactory(new PropertyValueFactory<>("transicionesPermitidas"));
        tblReglasDeTransicion.setCellValueFactory(new PropertyValueFactory<>("reglasDeTransicion")); // Modificado
        tblAccionesColumna.setCellValueFactory(new PropertyValueFactory<>("accionesAutomaticas"));

        
        // ejemploo
        listaFlujos.add(new FlujoTrabajo("Flujo 1", "Estado 1, Estado 2", "Transición A", "Reglas de Transición 1", "Acciones 1"));
    }
    
    
    @FXML
private void handleCrearFlujo(ActionEvent event) {
    try {
        // Cargar la vista del formulario de creación de flujos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionFlujosTrabajo2Vista.fxml"));
        Parent root = loader.load();

        // Obtener el controlador del formulario de flujos
        GestionFlujosTrabajo2Controller controller = loader.getController();

        // Cargar los estados desde GestionEstadosTicketsController y GestionEstadosTicketsVista
        FXMLLoader estadosLoader = new FXMLLoader(getClass().getResource("/vista/GestionEstadosTicketsVista.fxml"));
        estadosLoader.load();
        GestionEstadosTicketsController estadosController = estadosLoader.getController();
        ObservableList<EstadoTicket> listaEstados = estadosController.getListaEstados();

        // Pasar los estados al controlador del formulario de flujos
        controller.setListaEstados(listaEstados);

        // Pasar la lista de flujos al controlador del formulario de flujos
        controller.setListaFlujos(this.listaFlujos);

        // Limpiar los campos para un nuevo registro
        controller.limpiarCampos();

        // Crear un nuevo Stage para la nueva interfaz
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Crear Nuevo Flujo de Trabajo");
        newStage.show();

        // Cerrar la ventana actual si es necesario
        Stage currentStage = (Stage) buttonCrearFlujo.getScene().getWindow();
        currentStage.close(); // Cierra la ventana actual
    } catch (IOException e) {
        // Mostrar un mensaje de error si no se puede cargar la vista
        mostrarMensajeError("Error al abrir el formulario: " + e.getMessage());
        e.printStackTrace(); // Imprimir detalles del error para depuración
    }
}

    
/*
    @FXML
    private void handleCrearFlujo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionFlujosTrabajo2Vista.fxml"));
            Parent root = loader.load();

          
            GestionFlujosTrabajo2Controller controller = loader.getController();
            controller.setListaEstados(listaEstados); // Pasar la lista de estados
            controller.setListaFlujos(listaFlujos);
             //El formulario estará limpio
             controller.limpiarCampos();
             
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Crear Nuevo Flujo de Trabajo");
            stage.show();

             // Cerrar la ventana actual
            Stage currentStage = (Stage) buttonCrearFlujo.getScene().getWindow();
            currentStage.close(); // Cierra la ventana actual
            
        } catch (IOException e) {
            mostrarMensajeError("Error al abrir el formulario: " + e.getMessage());
            e.printStackTrace();
        }
    }*/

    
    
            /*GestionFlujosTrabajo2Controller controller = loader.getController();
            controller.setListaFlujos(listaFlujos);
            controller.configurarModoModificar(seleccionado);*/
            
    @FXML
private void handleModificarFlujo(ActionEvent event) {
    FlujoTrabajo seleccionado = tblFlujosTrabajo.getSelectionModel().getSelectedItem();
    if (seleccionado == null) {
        mostrarMensajeError("Debe seleccionar un flujo de trabajo para modificar.");
        return;
    }

    try {
        // Cargar la vista del formulario de modificación
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionFlujosTrabajo2Vista.fxml"));
        Parent root = loader.load();

        // Obtener el controlador del formulario
        GestionFlujosTrabajo2Controller controller = loader.getController();

        // Cargar los estados desde GestionEstadosTicketsController y GestionEstadosTicketsVista
        FXMLLoader estadosLoader = new FXMLLoader(getClass().getResource("/vista/GestionEstadosTicketsVista.fxml"));
        estadosLoader.load();
        GestionEstadosTicketsController estadosController = estadosLoader.getController();
        ObservableList<EstadoTicket> listaEstados = estadosController.getListaEstados();

        // Pasar los estados y flujos al formulario
        controller.setListaEstados(listaEstados);
        controller.setListaFlujos(listaFlujos);

        // Configurar el flujo seleccionado en modo modificación
        controller.configurarModoModificar(seleccionado);

        // Crear un nuevo Stage para la nueva interfaz
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Modificar Flujo de Trabajo");
        newStage.show();

        // Cerrar la ventana actual
        Stage currentStage = (Stage) buttonModificarFlujo.getScene().getWindow();
        currentStage.close(); // Cierra la ventana actual
    } catch (IOException e) {
        mostrarMensajeError("Error al abrir el formulario: " + e.getMessage());
        e.printStackTrace();
    }
}


    @FXML
    private void handleEliminarFlujo(ActionEvent event) {
        FlujoTrabajo seleccionado = tblFlujosTrabajo.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarMensajeError("Debe seleccionar un flujo de trabajo para eliminar.");
            return;
            
        }

        listaFlujos.remove(seleccionado);
        mostrarMensajeInfo("Departamento eliminado exitosamente.");
    }

    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
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
       
       
     public void setListaFlujos(ObservableList<FlujoTrabajo> listaFlujos) {
    this.listaFlujos = listaFlujos;
    tblFlujosTrabajo.setItems(this.listaFlujos); // Vincular la lista al TableView
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
            // Recuperar el controlador de la vista principal
            GestionFlujosTrabajoController controller = loader.getController();
            controller.setListaFlujos(listaFlujos); // Actualizar la lista observable en el controlador principal
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionFlujosTrabajoController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }     
  }

}
    
    
    
    
    
    
    /*@FXML
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
    
    
    }*/
