/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.Ticket;

/**
 *
 * @author Karlaa
 */
public class GestionTicket2Controller implements Initializable {
    @FXML
    private TextField textTituloTicket;
    @FXML
    private TextArea textDescripcionTicket;
    @FXML
    private ComboBox<String> comboBoxPrioridadTicket;
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonRegresar;
    @FXML
    private Button buttonCancelar;
    
    private ObservableList<Ticket> listaTickets;
    private Ticket ticketEnEdicion;
    
     /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar niveles de prioridad
        comboBoxPrioridadTicket.getItems().addAll("Alta", "Media", "Baja");
    }

    public void setListaTickets(ObservableList<Ticket> listaTickets) {
        this.listaTickets = listaTickets;
    }

   
    public void configurarModoModificar(Ticket ticket) {
    this.ticketEnEdicion = ticket;
    textTituloTicket.setText(ticket.getTitulo());
    textDescripcionTicket.setText(ticket.getDescripcion());
    comboBoxPrioridadTicket.setValue(ticket.getPrioridad());
}


    @FXML
    private void handleGuardar(ActionEvent event) {
        String titulo = textTituloTicket.getText();
        String descripcion = textDescripcionTicket.getText();
        String prioridad = comboBoxPrioridadTicket.getValue();

        if (titulo == null || titulo.trim().isEmpty()) {
            mostrarMensajeError("El título no puede estar vacío.");
            return;
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            mostrarMensajeError("La descripción no puede estar vacía.");
            return;
        }
        if (prioridad == null) {
            mostrarMensajeError("Debe seleccionar una prioridad.");
            return;
        }

        if (ticketEnEdicion == null) {
            Ticket nuevoTicket = new Ticket(titulo, descripcion, "Departamento no asignado", prioridad);
            listaTickets.add(nuevoTicket);
        } else {
            ticketEnEdicion.setTitulo(titulo);
            ticketEnEdicion.setDescripcion(descripcion);
            ticketEnEdicion.setPrioridad(prioridad);
        }

        cerrarVentana();
    }

    @FXML
    private void handleRegresar(ActionEvent event) {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) buttonRegresar.getScene().getWindow();
        stage.close();
    }

    private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();    
    }
    
    public void limpiarCampos() {
    textTituloTicket.clear();
    textDescripcionTicket.clear();
    comboBoxPrioridadTicket.setValue(null);
}
    
    
}


