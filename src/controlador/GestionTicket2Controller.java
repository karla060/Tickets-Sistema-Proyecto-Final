/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.GestionTicketsDAO;
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

    
    private String validarCampos() {
    StringBuilder errores = new StringBuilder();

    // Validar que el título no esté vacío
    String titulo = textTituloTicket.getText();
    if (titulo == null || titulo.trim().isEmpty()) {
        errores.append("El título no puede estar vacío.\n");
    }

    // Validar que la descripción no esté vacía
    String descripcion = textDescripcionTicket.getText();
    if (descripcion == null || descripcion.trim().isEmpty()) {
        errores.append("La descripción no puede estar vacía.\n");
    }

    // Validar que se haya seleccionado una prioridad
    if (comboBoxPrioridadTicket.getValue() == null) {
        errores.append("Debe seleccionar una prioridad.\n");
    }

    return errores.toString(); // Retorna los errores acumulados como una cadena
    
}
   /* 
    @FXML
private void handleGuardar(ActionEvent event) {
    String errores = validarCampos();
    if (!errores.isEmpty()) {
        mostrarMensajeError(errores);
        return;
    }

    String titulo = textTituloTicket.getText();
    String descripcion = textDescripcionTicket.getText();
    String prioridad = comboBoxPrioridadTicket.getValue();
    
    GestionTicketsDAO ticketDAO = new GestionTicketsDAO();

    if (ticketEnEdicion == null) {
        // Crear un nuevo ticket
        Ticket nuevoTicket = new Ticket(0, titulo, descripcion, "Departamento no asignado", prioridad);
        listaTickets.add(nuevoTicket);
        ticketDAO.guardarTicket(nuevoTicket); // Guardar en BD
    } else {
        // Modificar ticket existente
        ticketEnEdicion.setTitulo(titulo);
        ticketEnEdicion.setDescripcion(descripcion);
        ticketEnEdicion.setPrioridad(prioridad);
        ticketDAO.guardarTicket(ticketEnEdicion); // Guardar en BD
        
        int index = listaTickets.indexOf(ticketEnEdicion);
        if (index != -1) {
            listaTickets.set(index, ticketEnEdicion);
        }
    }

    volverAVistaPrincipal();
}*/

    
      @FXML
    private void handleGuardar(ActionEvent event) {
        String errores = validarCampos();
        if (!errores.isEmpty()) {
            mostrarMensajeError(errores);
            return;
        }
        
        String titulo = textTituloTicket.getText();
        String descripcion = textDescripcionTicket.getText();
        String prioridad = comboBoxPrioridadTicket.getValue();

        // Se crea una instancia del DAO para usar su método de persistencia
        GestionTicketsDAO ticketDAO = new GestionTicketsDAO();

        if (ticketEnEdicion == null) {
            // Crear un nuevo ticket. Se usa "Departamento no asignado" por defecto.
            Ticket nuevoTicket = new Ticket(0, titulo, descripcion, "Departamento no asignado", prioridad);
            listaTickets.add(nuevoTicket);
            ticketDAO.guardarTicket(nuevoTicket);  // Inserta el nuevo ticket en la BD
        } else {
            // Modificar el ticket existente en memoria y en la BD
            ticketEnEdicion.setTitulo(titulo);
            ticketEnEdicion.setDescripcion(descripcion);
            ticketEnEdicion.setPrioridad(prioridad);
            int index = listaTickets.indexOf(ticketEnEdicion);
            if (index != -1) {
                listaTickets.set(index, ticketEnEdicion);
            }
            ticketDAO.guardarTicket(ticketEnEdicion);  // Actualiza en la BD
        }

        volverAVistaPrincipal();
    }
   /* 

    @FXML
    private void handleGuardaar(ActionEvent event) {
    // Validar los campos antes de guardar
    String errores = validarCampos();
    if (!errores.isEmpty()) {
        mostrarMensajeError(errores); // Mostrar los errores si existen
        return; // Detener la ejecución si hay errores
    }

    // Si no hay errores, proceder con el guardado
    String titulo = textTituloTicket.getText();
    String descripcion = textDescripcionTicket.getText();
    String prioridad = comboBoxPrioridadTicket.getValue();

    if (ticketEnEdicion == null) {
        // Crear un nuevo ticket si no hay ticket en edición
        Ticket nuevoTicket = new Ticket(0,titulo, descripcion, "Departamento no asignado", prioridad);
        listaTickets.add(nuevoTicket);
    } else {
        // Modificar el ticket en edición
        ticketEnEdicion.setTitulo(titulo);
        ticketEnEdicion.setDescripcion(descripcion);
        ticketEnEdicion.setPrioridad(prioridad);

        // Reflejar los cambios en la lista observable
        int index = listaTickets.indexOf(ticketEnEdicion);
        if (index != -1) {
            listaTickets.set(index, ticketEnEdicion);
        }
    }

    volverAVistaPrincipal(); // Volver a la vista principal
}*/

    private void volverAVistaPrincipal() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionTicketVista.fxml"));
        Parent root = loader.load();

        // Obtener el controlador principal
        GestionTicketController controller = loader.getController();
        controller.setListaTickets(listaTickets); // Pasar la lista de tickets al controlador principal

        // Cambiar la escena
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

     @FXML
    private void handleRegresar(ActionEvent event) {
        volverAVistaPrincipal();
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


