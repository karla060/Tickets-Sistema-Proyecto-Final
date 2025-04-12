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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import modelo.EstadoTicket;

/**
 *
 * @author Karlaa
 */



public class GestionEstadosTickets2Controller implements Initializable{
   @FXML
    private TextField  textNombreEstado;
    @FXML
    private TextArea textDescripcionEstado;
    @FXML
    private ListView<CheckBox> listEstadoFinal;
    @FXML
    private ListView<CheckBox> listEstadosSiguientes;
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonRegresar;

    private ObservableList<EstadoTicket> listaEstados;
    private EstadoTicket estadoEnEdicion;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar listas (puedes llenarlas según corresponda)
        listEstadoFinal.getItems().add(new CheckBox("Sí"));
        listEstadoFinal.getItems().add(new CheckBox("No"));

        listEstadosSiguientes.getItems().add(new CheckBox("Resuelto"));
        listEstadosSiguientes.getItems().add(new CheckBox("Cerrado"));
        listEstadosSiguientes.getItems().add(new CheckBox("Pendiente"));
    }
    
    
    


    public void setListaEstados(ObservableList<EstadoTicket> listaEstados) {
        this.listaEstados = listaEstados;
    }
    
/*    
    public void setListaEstados(ObservableList<EstadoTicket> listaEstados) {
    // Convertir la lista de estados en CheckBoxes y agregarlos al ListView
    listEstadosInvolucrados.getItems().clear(); // Limpiar elementos previos
    listaEstados.forEach(estado -> {
        CheckBox checkBox = new CheckBox(estado.getNombreEstado());
        listEstadosInvolucrados.getItems().add(checkBox);
    });
}*/

    public void configurarModoModificar(EstadoTicket estado) {
    /*Guardar una referencia al estado que se está editando para modificar sus datos directamente en lugar de crear uno nuevo*/
    estadoEnEdicion = estado;

    // Configurar el nombre y descripción del estado en los campos de texto
    textNombreEstado.setText(estado.getNombreEstado());
    textDescripcionEstado.setText(estado.getDescripcionEstado());

    // Recorrer todos los estados finales disponibles en la lista de selección
    listEstadoFinal.getItems().forEach(checkBox -> {
        // Comparar el texto del CheckBox con el estado final asignado al estado
        checkBox.setSelected(estado.getEstadoFinal().equals(checkBox.getText()));
    });

    // Recorrer todos los estados siguientes disponibles en la lista de selección
    listEstadosSiguientes.getItems().forEach(checkBox -> {
        // Comparar el texto del CheckBox con los estados siguientes asignados al estado
        checkBox.setSelected(estado.getEstadosSiguientes().contains(checkBox.getText()));
    });
}


@FXML
private void handleGuardar(ActionEvent event) {
    StringBuilder errores = new StringBuilder();

    // Validar el campo "Nombre del Estado"
    String nombre = textNombreEstado.getText();
    if (nombre == null || nombre.isEmpty() || nombre.length() < 3 || nombre.length() > 50) {
        errores.append("El nombre del estado debe contener entre 3 y 50 caracteres.\n");
    }

    // Validar el campo "Estado Final"
    String estadoFinal = listEstadoFinal.getItems().stream()
        .filter(CheckBox::isSelected) // Verificar que al menos un estado final esté seleccionado
        .map(CheckBox::getText)
        .findFirst()
        .orElse(null);
    if (estadoFinal == null) {
        errores.append("Debe seleccionar un estado final.\n");
    }

    // Validar el campo "Estados Siguientes"
    String estadosSiguientes = listEstadosSiguientes.getItems().stream()
        .filter(CheckBox::isSelected) // Verificar que al menos un estado siguiente esté seleccionado
        .map(CheckBox::getText)
        .reduce((s1, s2) -> s1 + ", " + s2)
        .orElse(null);
    if (estadosSiguientes == null) {
        errores.append("Debe seleccionar al menos un estado siguiente.\n");
    }

    // Si hay errores, mostrarlos en una alerta y salir del método
    if (errores.length() > 0) {
        mostrarMensajeError(errores.toString());
        return;
    }

    
    String descripcion = textDescripcionEstado.getText(); // La descripción es opcional
    
    // Crear o modificar el estado (cuando no hay errores)
    if (estadoEnEdicion == null) {
        EstadoTicket nuevoEstado = new EstadoTicket(nombre, descripcion, estadoFinal, estadosSiguientes);
        listaEstados.add(nuevoEstado);
    } else {
        estadoEnEdicion.setNombreEstado(nombre);
        estadoEnEdicion.setDescripcionEstado(descripcion); // Puede ser nula o vacía
        estadoEnEdicion.setEstadoFinal(estadoFinal);
        estadoEnEdicion.setEstadosSiguientes(estadosSiguientes);
    }

    // Redirigir a la vista principal
    volverAVistaPrincipal();
}

    
    private void volverAVistaPrincipal() {
    try {
        // Cargar la vista principal (Gestión de Estados de Tickets)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionEstadosTicketsVista.fxml"));
        Parent root = loader.load();

        // Recuperar el controlador de la vista principal
        GestionEstadosTicketsController controller = loader.getController();
        controller.setListaEstados(listaEstados); // Actualizar la lista observable en el controlador principal

        // Cambiar la escena al Stage actual
        Stage stage = (Stage) buttonGuardar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Gestión de Estados de Tickets");
        stage.show();
        
    } catch (IOException e) {
        mostrarMensajeError("Error al regresar a la vista principal: " + e.getMessage());
        e.printStackTrace();
    }
}

    
    public void limpiarCampos() {
    // Limpiar los campos de texto
    textNombreEstado.clear();
    textDescripcionEstado.clear();

    // Limpiar las selecciones de las ListView
    listEstadoFinal.getSelectionModel().clearSelection();
    listEstadosSiguientes.getSelectionModel().clearSelection();

    // Desmarcar cualquier CheckBox en las ListView
    listEstadoFinal.getItems().forEach(checkBox -> checkBox.setSelected(false));
    listEstadosSiguientes.getItems().forEach(checkBox -> checkBox.setSelected(false));
}

    @FXML
    private void handleRegresar(ActionEvent event) {
    volverAVistaPrincipal();
    }


    @FXML
    private void handleCancelar(ActionEvent event) {
         limpiarCampos();
        mostrarMensajeInfo("La operación fue cancelada.");
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
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    

}
