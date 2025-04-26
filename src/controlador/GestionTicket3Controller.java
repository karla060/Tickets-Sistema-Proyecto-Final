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

    private ObservableList<Ticket> listaTickets;
    private Ticket ticketEnEdicion;

    
     /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Cargar los departamentos existentes desde el controlador de departamentos
        cargarDepartamentos();
    }

    public void setListaTickets(ObservableList<Ticket> listaTickets) {
        this.listaTickets = listaTickets;
    }

    public void configurarModoModificar(Ticket ticket) {
        this.ticketEnEdicion = ticket;
        comboBoxDepartamento.setValue(ticket.getDepartamento()); // Seleccionar el departamento actual
    }

    private String validarCampos() {
        StringBuilder errores = new StringBuilder();

        // Validar que se haya seleccionado un departamento
        String departamento = comboBoxDepartamento.getValue();
        if (departamento == null || departamento.trim().isEmpty()) {
            errores.append("Debe seleccionar un departamento válido.\n");
        }

        return errores.toString();
    }

    @FXML
    private void handleGuardar(ActionEvent event) {
        // Validar los campos antes de guardar
        String errores = validarCampos();
        if (!errores.isEmpty()) {
            mostrarMensajeError(errores);
            return;
        }

        // Obtener el departamento seleccionado
        String departamento = comboBoxDepartamento.getValue();

        if (ticketEnEdicion != null) {
            // Modificar el ticket en edición
            ticketEnEdicion.setDepartamento(departamento);

            int index = listaTickets.indexOf(ticketEnEdicion);
            if (index != -1) {
                listaTickets.set(index, ticketEnEdicion);
            }
        } else {
            mostrarMensajeError("No hay ningún ticket seleccionado para modificar.");
        }

        volverAVistaPrincipal();
    }

    private void volverAVistaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionTicketVista.fxml"));
            Parent root = loader.load();

            GestionTicketController controller = loader.getController();
            controller.setListaTickets(listaTickets);

            Stage stage = (Stage) buttonGuardar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Tickets");
            stage.show();
            
                   
            // Cerrar la ventana actual
            Stage currentStage = (Stage) buttonGuardar.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            mostrarMensajeError("Error al regresar a la vista principal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void cargarDepartamentos() {
        try {
            // Cargar el controlador de departamentos
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionDepartamentosVista.fxml"));
            loader.load();
            GestionDepartamentosController controller = loader.getController();

            // Obtener los nombres de los departamentos desde el controlador
            ObservableList<String> nombresDepartamentos = controller.getNombresDeDepartamentos();

            // Cargar los departamentos en el ComboBox
            comboBoxDepartamento.getItems().clear();
            comboBoxDepartamento.getItems().addAll(nombresDepartamentos);
        } catch (IOException e) {
            mostrarMensajeError("Error al cargar los departamentos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    @FXML
    private void handleRegresar(ActionEvent event) {
        volverAVistaPrincipal();
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