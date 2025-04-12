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
import modelo.EstadoTicket;
import modelo.FlujoTrabajo;

/**
 *
 * @author Karlaa
 */
public class GestionFlujosTrabajo2Controller implements Initializable {

    @FXML
    private TextField textNombreFlujo;
    @FXML
    private ListView<CheckBox> listEstadosInvolucrados;
    @FXML
    private ListView<CheckBox> listTransicionesPermitidas;
    @FXML
    private TextArea textReglasDeTransicion; 
    @FXML
    private TextArea textAccionesAutomaticas;
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonRegresar;

   /* private ObservableList<FlujoTrabajo> listaFlujos;
    private FlujoTrabajo flujoEnEdicion;*/
    
    private ObservableList<FlujoTrabajo> listaFlujos;
    private ObservableList<EstadoTicket> listaEstados;
    private FlujoTrabajo flujoEnEdicion;

    
     /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar los ListView si corresponde
        listTransicionesPermitidas.getItems().addAll(new CheckBox("Transición A"), new CheckBox("Transición B"));
    }

    public void setListaFlujos(ObservableList<FlujoTrabajo> listaFlujos) {
        this.listaFlujos = listaFlujos;
    }

  /*  
  public void setListaEstados(ObservableList<EstadoTicket> listaEstados) {
    if (listaEstados == null || listaEstados.isEmpty()) {
        mostrarMensajeInfo("No hay estados disponibles para involucrar en el flujo.");
        return;
    }

    this.listaEstados = listaEstados;
    listEstadosInvolucrados.getItems().clear();
    listaEstados.forEach(estado -> {
        CheckBox checkBox = new CheckBox(estado.getNombreEstado());
        listEstadosInvolucrados.getItems().add(checkBox);
    });
}*/

    public void setListaEstados(ObservableList<EstadoTicket> listaEstados) {
    this.listaEstados = listaEstados;

    // Limpiar y cargar la lista de estados en el ListView o ComboBox
    listEstadosInvolucrados.getItems().clear();
    listaEstados.forEach(estado -> {
        CheckBox checkBox = new CheckBox(estado.getNombreEstado());
        listEstadosInvolucrados.getItems().add(checkBox);
    });
}




    public void configurarModoModificar(FlujoTrabajo flujo) {
        flujoEnEdicion = flujo;
        textNombreFlujo.setText(flujo.getNombreFlujo());
        textReglasDeTransicion.setText(flujo.getReglasDeTransicion()); // Modificado
        textAccionesAutomaticas.setText(flujo.getAccionesAutomaticas());

        listEstadosInvolucrados.getItems().forEach(checkBox -> 
            checkBox.setSelected(flujo.getEstadosInvolucrados().contains(checkBox.getText())));

        listTransicionesPermitidas.getItems().forEach(checkBox -> 
            checkBox.setSelected(flujo.getTransicionesPermitidas().contains(checkBox.getText())));
    }

    private String validarCampos() {
    StringBuilder errores = new StringBuilder();

    // Validar que el nombre del flujo no esté vacío
    String nombre = textNombreFlujo.getText();
    if (nombre == null || nombre.trim().isEmpty()) {
        errores.append("El nombre del flujo no puede estar vacío.\n");
    } else {
        // Validar que el nombre no exista ya en la lista de flujos
        boolean nombreDuplicado = listaFlujos.stream()
            .anyMatch(flujo -> flujo.getNombreFlujo().equalsIgnoreCase(nombre) && flujo != flujoEnEdicion);
        if (nombreDuplicado) {
            errores.append("El nombre del flujo ya existe. Por favor, elija un nombre único.\n");
        }
    }

    // Validar que las reglas de transición no estén vacías
    if (textReglasDeTransicion.getText() == null || textReglasDeTransicion.getText().trim().isEmpty()) {
        errores.append("Las reglas de transición no pueden estar vacías.\n");
    }

    // Validar que se seleccionen al menos un estado involucrado
    boolean tieneEstadosSeleccionados = listEstadosInvolucrados.getItems().stream()
        .anyMatch(CheckBox::isSelected);
    if (!tieneEstadosSeleccionados) {
        errores.append("Debe seleccionar al menos un estado involucrado.\n");
    }

    // Validar que se seleccionen al menos una transición permitida
    boolean tieneTransicionesSeleccionadas = listTransicionesPermitidas.getItems().stream()
        .anyMatch(CheckBox::isSelected);
    if (!tieneTransicionesSeleccionadas) {
        errores.append("Debe seleccionar al menos una transición permitida.\n");
    }

    return errores.toString(); // Retorna los errores acumulados como una cadena
}

    
    @FXML
    private void handleGuardar(ActionEvent event) {
    // Validar los campos antes de guardar
    String errores = validarCampos();
    if (!errores.isEmpty()) {
        mostrarMensajeError(errores); // Mostrar los errores si existen
        return; // Detener la ejecución si hay errores
    }

    // Si no hay errores, proceder con el guardado
    String nombre = textNombreFlujo.getText();
    String reglas = textReglasDeTransicion.getText();
    String acciones = textAccionesAutomaticas.getText(); // Opcional

    String estadosInvolucrados = listEstadosInvolucrados.getItems().stream()
        .filter(CheckBox::isSelected)
        .map(CheckBox::getText)
        .reduce((e1, e2) -> e1 + ", " + e2)
        .orElse("");

    String transiciones = listTransicionesPermitidas.getItems().stream()
        .filter(CheckBox::isSelected)
        .map(CheckBox::getText)
        .reduce((t1, t2) -> t1 + ", " + t2)
        .orElse("");

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

    
     private void mostrarMensajeError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
     public void limpiarCampos() {
    // Método para limpiar todos los campos del formulario de flujos de trabajo
    textNombreFlujo.clear(); 
    textReglasDeTransicion.clear(); 
    textAccionesAutomaticas.clear();
    // Desmarcar todos los estados involucrados en la lista
    listEstadosInvolucrados.getItems().forEach(checkBox -> checkBox.setSelected(false));
    // Desmarcar todas las transiciones permitidas en la lista
    listTransicionesPermitidas.getItems().forEach(checkBox -> checkBox.setSelected(false));
}

    private void mostrarMensajeInfo(String mensaje) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Información");
    alert.setContentText(mensaje);
    alert.showAndWait();
    }
    
    @FXML
    private void handleCancelar(ActionEvent event) {
        limpiarCampos();
        mostrarMensajeInfo("La operación fue cancelada.");
        
    }
    
    
    @FXML
    private void handleRegresar(ActionEvent event) {
    volverAVistaPrincipal();
    }
}


/*
    @FXML
    private void handleGuardar(ActionEvent event) {
        //obtener valores ingresados 
        String nombre = textNombreFlujo.getText();
        String reglas = textReglasDeTransicion.getText(); // Modificado
        String acciones = textAccionesAutomaticas.getText();

        String estadosInvolucrados = listEstadosInvolucrados.getItems().stream()
            .filter(CheckBox::isSelected)
            .map(CheckBox::getText)
            .reduce((e1, e2) -> e1 + ", " + e2)
            .orElse("");

        String transiciones = listTransicionesPermitidas.getItems().stream()
            .filter(CheckBox::isSelected)
            .map(CheckBox::getText)
            .reduce((t1, t2) -> t1 + ", " + t2)
            .orElse("");

        if (flujoEnEdicion == null) {
            FlujoTrabajo nuevoFlujo = new FlujoTrabajo(nombre, estadosInvolucrados, transiciones, reglas, acciones);
            listaFlujos.add(nuevoFlujo);
        } else {
            flujoEnEdicion.setNombreFlujo(nombre);
            flujoEnEdicion.setEstadosInvolucrados(estadosInvolucrados);
            flujoEnEdicion.setTransicionesPermitidas(transiciones);
            flujoEnEdicion.setReglasDeTransicion(reglas); // Modificado
            flujoEnEdicion.setAccionesAutomaticas(acciones);
        }

        volverAVistaPrincipal();
    }*/
    
     /*
    @FXML
private void handleGuardar(ActionEvent event) {
    // Validar que el nombre del flujo no esté vacío
    String nombre = textNombreFlujo.getText();
    if (nombre == null || nombre.trim().isEmpty()) {
        mostrarMensajeError("El nombre del flujo no puede estar vacío.");
        return;
    }

    // Obtener las demás entradas
    String reglas = textReglasDeTransicion.getText();
    String acciones = textAccionesAutomaticas.getText();

    String estadosInvolucrados = listEstadosInvolucrados.getItems().stream()
        .filter(CheckBox::isSelected)
        .map(CheckBox::getText)
        .reduce((e1, e2) -> e1 + ", " + e2)
        .orElse("");

    String transiciones = listTransicionesPermitidas.getItems().stream()
        .filter(CheckBox::isSelected)
        .map(CheckBox::getText)
        .reduce((t1, t2) -> t1 + ", " + t2)
        .orElse("");

    // Validar que se seleccionen al menos un estado involucrado
    if (estadosInvolucrados.isEmpty()) {
        mostrarMensajeError("Debe seleccionar al menos un estado involucrado.");
        return;
    }

    // Crear o modificar flujo
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

    volverAVistaPrincipal();
}*/