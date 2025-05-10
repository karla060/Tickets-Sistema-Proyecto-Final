/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import modelo.DepartamentoDAO;
import modelo.DepartamentoTecnicoDAO;
import modelo.TecnicoDAO;

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
    public void initialize(URL url, java.util.ResourceBundle rb) {
        // Ejemplo de técnicos disponibles. Puedes cambiar o cargar desde otra fuente.
       listtTecnicosExistentes.getItems().addAll(
            new CheckBox("Juan"),
            new CheckBox("Ana"),
            new CheckBox("Carlos"),
            new CheckBox("Pedro"),
            new CheckBox("Luisa")
        );
    }
    
    // Si se va a modificar un departamento, se configura el formulario con los datos existentes.
    public void configurarModoModificar(Departamento departamento) {
        departamentoEnEdicion = departamento;
        textNombreDepa.setText(departamento.getNombre());
        textDescripcionDepa.setText(departamento.getDescripcion());
        
        // Marcar en la lista los técnicos que ya están asignados (la cadena se compara con el texto del CheckBox)
        listtTecnicosExistentes.getItems().forEach(checkBox -> {
            checkBox.setSelected(departamento.getTecnicosAsignados().contains(checkBox.getText()));
        });
    }
    
    @FXML
    private void handleGuardarDepa() {
        String nombre = textNombreDepa.getText();
        String descripcion = textDescripcionDepa.getText();
        
        if (nombre == null || nombre.trim().isEmpty() || nombre.trim().length() < 3 || nombre.trim().length() > 50) {
            mostrarMensajeError("El nombre del departamento debe contener entre 3 y 50 caracteres.");
            return;
        }
        
        // Concatenar los nombres de los técnicos seleccionados en una sola cadena separada por comas
        String tecnicosAsignados = listtTecnicosExistentes.getItems().stream()
            .filter(CheckBox::isSelected)
            .map(CheckBox::getText)
            .reduce((t1, t2) -> t1 + ", " + t2)
            .orElse("");
        
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        
        if (departamentoEnEdicion == null) {
            // Crear un nuevo departamento
            Departamento nuevoDepartamento = new Departamento(nombre.trim(), descripcion.trim(), tecnicosAsignados);
            departamentoDAO.insertarDepartamento(nuevoDepartamento);
            listaDepartamentos.add(nuevoDepartamento);
        } else {
            // Actualizar el departamento existente
            departamentoEnEdicion.setNombre(nombre.trim());
            departamentoEnEdicion.setDescripcion(descripcion.trim());
            departamentoEnEdicion.setTecnicosAsignados(tecnicosAsignados);
            departamentoDAO.actualizarDepartamento(departamentoEnEdicion);
        }
        
        volverAVistaPrincipal();
    }
    
  /* @FXML
private void handleGuardarDepa() {
    String nombre = textNombreDepa.getText();
    String descripcion = textDescripcionDepa.getText();
    
    if (nombre == null || nombre.trim().isEmpty() || nombre.trim().length() < 3 || nombre.trim().length() > 50) {
        mostrarMensajeError("El nombre del departamento debe contener entre 3 y 50 caracteres.");
        return;
    }
    
    // Recolectar los nombres de los técnicos seleccionados
    List<String> tecnicosSeleccionados = new ArrayList<>();
    listtTecnicosExistentes.getItems().forEach(checkBox -> {
        if (checkBox.isSelected()) {
            tecnicosSeleccionados.add(checkBox.getText());
        }
    });
    
    DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    DepartamentoTecnicoDAO dtDAO = new DepartamentoTecnicoDAO();
    TecnicoDAO tecnicoDAO = new TecnicoDAO();
    
    if (departamentoEnEdicion == null) {
        // Crear un nuevo departamento
        Departamento nuevoDepartamento = new Departamento(nombre.trim(), descripcion.trim(), "");
        departamentoDAO.insertarDepartamento(nuevoDepartamento);
        listaDepartamentos.add(nuevoDepartamento);
        
        // Actualizar la tabla intermedia: para cada técnico seleccionado, buscar su id y crear la relación
        List<Integer> tecnicosIds = new ArrayList<>();
        for (String nombreTecnico : tecnicosSeleccionados) {
            int idTecnico = tecnicoDAO.obtenerIdTecnicoPorNombre(nombreTecnico);
            if (idTecnico != -1) {
                tecnicosIds.add(idTecnico);
            }
        }
        dtDAO.asignarTodosLosTecnicos(nuevoDepartamento.getIdDepartamento(), tecnicosIds);
    } else {
        // Actualizar el departamento existente
        // Primero, actualizamos los datos principales.
        departamentoEnEdicion.setNombre(nombre.trim());
        departamentoEnEdicion.setDescripcion(descripcion.trim());
        departamentoDAO.actualizarDepartamento(departamentoEnEdicion);
        
        // Luego, actualizar la tabla intermedia:
        // Eliminamos primero todas las relaciones previas.
        dtDAO.eliminarTecnicosPorDepartamento(departamentoEnEdicion.getIdDepartamento());
        // Recopilamos los id de los técnicos seleccionados.
        List<Integer> tecnicosIds = new ArrayList<>();
        for (String nombreTecnico : tecnicosSeleccionados) {
            int idTecnico = tecnicoDAO.obtenerIdTecnicoPorNombre(nombreTecnico);
            if (idTecnico != -1) {
                tecnicosIds.add(idTecnico);
            }
        }
        // Y asignamos los nuevos.
        dtDAO.asignarTodosLosTecnicos(departamentoEnEdicion.getIdDepartamento(), tecnicosIds);
    }
    
    volverAVistaPrincipal();
}*/

    
    private void volverAVistaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionDepartamentosVista.fxml"));
            Parent root = loader.load();
            GestionDepartamentosController controller = loader.getController();
            controller.setListaDepartamentos(listaDepartamentos);
            Stage stage = (Stage) buttonGuardar.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Departamentos");
            stage.show();
        } catch (IOException e) {
            mostrarMensajeError("Error al regresar a la vista principal: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleCancelar(ActionEvent event) {
        limpiarCampos();
        mostrarMensajeInfo("La operación fue cancelada.");
    }
    
    public void limpiarCampos() {
        textNombreDepa.clear();
        textDescripcionDepa.clear();
        listtTecnicosExistentes.getItems().forEach(checkBox -> checkBox.setSelected(false));
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
}
    
    
    
    
    
    
    
    
    
    /*
    
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
     //Guardar una referencia al departamento que se está editando para modificar sus datos directamente en lugar de crear uno nuevo
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
}*/