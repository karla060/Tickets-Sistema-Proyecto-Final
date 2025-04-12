/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.Departamento;
import modelo.Ticket;

/**
 *
 * @author Karlaa
 */

public class GestionTicket3Controller implements Initializable {
    @FXML
    private ComboBox<String> comboBoxDepartamento;
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonRegresar;

    private Ticket ticketEnEdicion;
    private ObservableList<Departamento> listaDepartamentos;
    private ObservableList<Ticket> listaTickets;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // La lista de departamentos será llenada desde otro controlador
         listaDepartamentos = FXCollections.observableArrayList(); 
         listaTickets = FXCollections.observableArrayList(); 
    }

    public void setListaDepartamentos(ObservableList<String> nombresDepartamentos) {
    comboBoxDepartamento.getItems().clear(); // Limpiar datos previos
    comboBoxDepartamento.getItems().addAll(nombresDepartamentos); // Agregar departamentos al ComboBox
}

    public void configurarTicket(Ticket ticket) {
    this.ticketEnEdicion = ticket;

    // Si el ticket ya tiene un departamento asignado, mostrarlo en el ComboBox
    comboBoxDepartamento.setValue(ticket.getDepartamento());
}

public void configurarModoModificar(Ticket ticket) {
    this.ticketEnEdicion = ticket;

    // Configurar el departamento del ticket en el ComboBox
    if (ticket != null && ticket.getDepartamento() != null) {
        comboBoxDepartamento.setValue(ticket.getDepartamento()); // Seleccionar el departamento actual en el ComboBox
    } else {
        comboBoxDepartamento.setValue(null); // Si no tiene un departamento asignado, dejar vacío
    }
}


  /*
    @FXML
    private void handleGuardar(ActionEvent event) {
    // Obtener el departamento seleccionado
    String departamento = comboBoxDepartamento.getValue();

    // Validar que el departamento no sea nulo o vacío
    if (departamento == null || departamento.trim().isEmpty()) {
        mostrarMensajeError("Debe seleccionar un departamento válido.");
        return; // Salir si hay un error
    }

    // Modificar el departamento del ticket
    if (ticketEnEdicion != null) {
        ticketEnEdicion.setDepartamento(departamento);
        mostrarMensajeInfo("El departamento ha sido asignado exitosamente al ticket.");
    }

    // Redirigir a la interfaz principal
    volverAVistaPrincipal();
}*/


   @FXML
private void handleGuardar(ActionEvent event) {
    // Obtener el departamento seleccionado
    String departamento = comboBoxDepartamento.getValue();

    // Validar que el departamento no sea nulo o vacío
    if (departamento == null || departamento.trim().isEmpty()) {
        mostrarMensajeError("Debe seleccionar un departamento válido.");
        return; // Salir si hay un error
    }

    // Modificar el departamento del ticket en edición
    if (ticketEnEdicion != null) {
        ticketEnEdicion.setDepartamento(departamento);

        mostrarMensajeInfo("El departamento ha sido modificado exitosamente.");
    } else {
        mostrarMensajeError("No hay ningún ticket seleccionado para modificar.");
    }

    // Redirigir a la interfaz principal
    volverAVistaPrincipal();
}

    
    
    /*
    
    if (flujoEnEdicion == null) {
        FlujoTrabajo nuevoFlujo = new FlujoTrabajo(nombre, estadosInvolucrados, transiciones, reglas, acciones);
        listaFlujos.add(nuevoFlujo);
    } else {
        flujoEnEdicion.setNombreFlujo(nombre);
        flujoEnEdicion.setEstadosInvolucrados(estadosInvolucrados);
        flujoEnEdicion.setTransicionesPermitidas(transiciones);
        flujoEnEdicion.setReglasDeTransicion(reglas);
        flujoEnEdicion.setAccionesAutomaticas(acciones);
    }

    volverAVistaPrincipal(); // Volver a la vista principal
}

    */
    
// Regresar a la vista principal de gestión de tickets
    private void volverAVistaPrincipal() {
        try {
            // Cargar la vista principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionTicketVista.fxml"));
            Parent root = loader.load();

            // Recuperar el controlador de la vista principal
            GestionTicketController controller = loader.getController();

            // Pasar la lista de tickets al controlador principal
            controller.setListaTickets(listaTickets);

            // Cambiar la escena al Stage actual
            Stage stage = (Stage) buttonGuardar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Tickets");
            stage.show();
        } catch (IOException e) {
            mostrarMensajeError("Error al regresar a la vista principal: " + e.getMessage());
            e.printStackTrace();
        }
    }
/*
      private void volverAVistaPrincipal() {
    try {
        // Cargar la vista principal (Gestión de Flujos de Trabajo)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionFlujosTrabajoVista.fxml"));
        Parent root = loader.load();

        // Recuperar el controlador de la vista principal
        GestionFlujosTrabajoController controller = loader.getController();
        controller.setListaFlujos(listaFlujos); // Actualizar la lista observable en el controlador principal

        // Cambiar la escena al Stage actual
        Stage stage = (Stage) buttonGuardar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Gestión de Flujos de Trabajo");
        stage.show();
    } catch (IOException e) {
        mostrarMensajeError("Error al regresar a la vista principal: " + e.getMessage());
        e.printStackTrace();
    }
}
    */

    @FXML
    private void handleRegresar(ActionEvent event) {
        volverAVistaPrincipal();
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
}




 /* public void setListaDepartamentos(ObservableList<Departamento> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;

        // Llenar el ComboBox con los nombres de los departamentos disponibles
        comboBoxDepartamento.getItems().clear();
        listaDepartamentos.forEach(departamento -> 
            comboBoxDepartamento.getItems().add(departamento.getNombre())
        );
    }*/

   /* public void setListaDepartamentos(ObservableList<Departamento> listaDepartamentos) {
     this.listaDepartamentos = listaDepartamentos;
// comboBoxDepartamento.getItems().clear(); // Limpiar los datos previos del ComboBox

    // Cargar los nombres de los departamentos en el ComboBox
    listaDepartamentos.forEach(departamento -> comboBoxDepartamento.getItems().add(departamento.getNombre()));
}*/