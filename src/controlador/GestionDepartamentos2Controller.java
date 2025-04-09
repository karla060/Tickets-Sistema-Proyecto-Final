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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Departamento;

/**
 *
 * @author Karlaa
 */

public class GestionDepartamentos2Controller implements Initializable{

    @FXML
    private TextField textNombreDepa; // Campo de texto para el nombre del departamento
    @FXML
    private TextArea textDescripcionDepa; // Área de texto para la descripción del departamento
    @FXML
    private ListView<CheckBox> listtTecnicosExistentes; // Lista para seleccionar técnicos
    @FXML
    private Button buttonGuardar; // Botón para guardar cambios
    @FXML
    private Button buttonCancelar; // Botón para cancelar la operación

    private ObservableList<Departamento> listaDepartamentos; // Lista observable compartida
    private Departamento departamentoEnEdicion; // Departamento que se está modificando

    public void setListaDepartamentos(ObservableList<Departamento> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }
    
     /**
     * Initializes the controller class.
     */
    
     @Override
     public void initialize(URL url, ResourceBundle rb) {
        // Técnicos disponibles como para dar un ejemplo
        listtTecnicosExistentes.getItems().addAll(
            new CheckBox("Juan"),
            new CheckBox("Ana"),
            new CheckBox("Carlos"),
            new CheckBox("Pedro"),
            new CheckBox("Luisa")
        );
    }

    public void configurarModoModificar(Departamento departamento) {
     /*Guardar una referencia al departamento que se está editando para modificar sus datos directamente en lugar de crear uno nuevo*/
        departamentoEnEdicion = departamento; 
        textNombreDepa.setText(departamento.getNombre());
        textDescripcionDepa.setText(departamento.getDescripcion());

         // Recorrer todos los técnicos disponibles en la lista de selección
        listtTecnicosExistentes.getItems().forEach(checkBox -> {
              
        // Se compara el texto del CheckBox con la lista de técnicos asignados al departamento
            checkBox.setSelected(departamento.getTecnicosAsignados().contains(checkBox.getText()));
        });
    }

    
     @FXML
    private void handleGuardarDepa() {
    // Obtener los valores ingresados en los campos del formulario
    String nombre = textNombreDepa.getText();
    String descripcion = textDescripcionDepa.getText();

    // Validar que el nombre cumpla con las reglas de longitud
    if (nombre == null || nombre.isEmpty() || nombre.length() < 3 || nombre.length() > 50) {
        mostrarMensajeError("El nombre del departamento debe contener entre 3 y 50 caracteres.");
        return;
    }

    // Obtener los técnicos asignados desde la lista
    String tecnicosAsignados = listtTecnicosExistentes.getItems().stream()
        .filter(CheckBox::isSelected) // Solo los técnicos seleccionados
        .map(CheckBox::getText) // Obtener los nombres de los técnicos
        .reduce((t1, t2) -> t1 + ", " + t2) // Combinar los nombres en una cadena separada por comas
        .orElse("");

    if (departamentoEnEdicion == null) {
        // Crear un nuevo departamento
        Departamento nuevoDepartamento = new Departamento(nombre, descripcion, tecnicosAsignados);
        listaDepartamentos.add(nuevoDepartamento); // Agregar a la lista observable compartida
       // mostrarMensajeInfo("El nuevo departamento ha sido creado exitosamente.");
    } else {
        // Modificar el departamento existente
        departamentoEnEdicion.setNombre(nombre);
        departamentoEnEdicion.setDescripcion(descripcion);
        departamentoEnEdicion.setTecnicosAsignados(tecnicosAsignados);
        //mostrarMensajeInfo("El departamento ha sido modificado exitosamente.");
    }

    // Redirigir a la vista principal (tabla de departamentos)
    volverAVistaPrincipal();
}


   private void volverAVistaPrincipal() {
    try {
        // Cargar la vista principal (Gestión de Departamentos)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionDepartamentosVista.fxml"));
        Parent root = loader.load();

        // Recuperar el controlador de la vista principal
        GestionDepartamentosController controller = loader.getController();
        controller.setListaDepartamentos(listaDepartamentos); // Actualizar la lista observable en el controlador principal

        // Cambiar la escena al Stage actual
        Stage stage = (Stage) buttonGuardar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Gestión de Departamentos");
        stage.show();
    } catch (IOException e) {
        mostrarMensajeError("Error al regresar a la vista principal: " + e.getMessage());
        e.printStackTrace();
    }
   }

     public void limpiarCampos() {
    //  Método para limpiar todos los campos 
    textNombreDepa.clear();
    textDescripcionDepa.clear();
    listtTecnicosExistentes.getItems().forEach(checkBox -> checkBox.setSelected(false));
}

    @FXML
    private void handleCancelar(ActionEvent event) {
        //Metodo para cancelar el proceso de creación o modificación de un departamento
        limpiarCampos();
        mostrarMensajeInfo("La operación fue cancelada.");
    }

    private void cerrarFormulario() {
        Stage stage = (Stage) buttonGuardar.getScene().getWindow();
        stage.close(); // Cerrar la ventana del formulario
    }

    private void mostrarMensajeError(String mensaje) {
        //método para mostrar un memnsaje en caso de que exista algún error al realizar procesos
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarMensajeInfo(String mensaje) {
        //metodo para mostrar un mensaje confirmando algo por ejemplo
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}