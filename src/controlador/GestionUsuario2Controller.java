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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import modelo.Usuario;
import modelo.Rol;
/**
 * FXML Controller class
 * @author Karlaa
 */
public class GestionUsuario2Controller implements Initializable{
 
   @FXML
    private TextField textNombreCompleto; 
    @FXML
    private TextField textEmail; 
    @FXML
    private TextField textNombreUsuario; 
    @FXML
    private PasswordField passContrasena; 
    @FXML
    private ComboBox<String> comboBoxRol; 
    @FXML
    private ComboBox<String> comboBoxDepartamento;
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;

    private ObservableList<Usuario> listaUsuarios;
    private Usuario usuarioEnEdicion;
   
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

 
    public void setUsuarioEnEdicion(Usuario usuario) {
        usuarioEnEdicion = usuario;

        textNombreCompleto.setText(usuario.getNombreCompleto());
        textEmail.setText(usuario.getEmail());
        textNombreUsuario.setText(usuario.getNombreUsuario());
        passContrasena.setText(usuario.getContrasena());
        comboBoxRol.setValue(usuario.getRol());
        comboBoxDepartamento.setValue(usuario.getDepartamento());
    }

    public void configurarModoModificar(Usuario usuario) {
    // Guardar una referencia al usuario que se está editando para modificar sus datos directamente
    usuarioEnEdicion = usuario;

    // Prellenar los campos de texto con los datos del usuario
    textNombreCompleto.setText(usuario.getNombreCompleto());
    textEmail.setText(usuario.getEmail());
    textNombreUsuario.setText(usuario.getNombreUsuario());
    passContrasena.setText(usuario.getContrasena());

    // Seleccionar el rol actual del usuario en el ComboBox de roles
    comboBoxRol.setValue(usuario.getRol());

    // Seleccionar el departamento actual del usuario en el ComboBox de departamentos
    comboBoxDepartamento.setValue(usuario.getDepartamento());
}

    public void setListaUsuarios(ObservableList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    
    public void setListaRoles(ObservableList<String> nombresRoles) {
    comboBoxRol.setItems(nombresRoles); 
   // Aquí se pasan los roles al ComboBox llamado 'comboBoxRol'
    }

    public void setListaDepartamentos(ObservableList<String> nombresDepartamentos) {
        comboBoxDepartamento.setItems(nombresDepartamentos);
    }

    
    public void setDepartamentoSegunRol() {
    // Listener para cambios en ComboBoxRol
    comboBoxRol.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if ("Técnico".equals(newValue)) {
            cargarDepartamentos(); // Llenar ComboBoxDepartamento si el rol es Técnico
            comboBoxDepartamento.setDisable(false); // Habilitar ComboBox
        } else {
            comboBoxDepartamento.getItems().clear(); // Vaciar ComboBoxDepartamento
            comboBoxDepartamento.setDisable(true); // Deshabilitar ComboBox
        }
    });
}

        private void cargarDepartamentos() {
    try {
        FXMLLoader departamentosLoader = new FXMLLoader(getClass().getResource("/vista/GestionDepartamentosVista.fxml"));
        departamentosLoader.load();
        GestionDepartamentosController departamentosController = departamentosLoader.getController();

        ObservableList<String> nombresDepartamentos = departamentosController.getNombresDeDepartamentos();
        comboBoxDepartamento.setItems(nombresDepartamentos); // Establecer departamentos en el ComboBox
    } catch (IOException e) {
        mostrarMensajeError("Error al cargar los departamentos: " + e.getMessage());
        e.printStackTrace();
    }
}

    //metodod para  limpiar los campos donde se realiza un registro  
    public void limpiarCampos() {
        textNombreCompleto.clear();
        textEmail.clear();
        textNombreUsuario.clear();
        passContrasena.clear();
        comboBoxRol.getSelectionModel().clearSelection();
        comboBoxDepartamento.getSelectionModel().clearSelection();
    }

 
    @FXML
    private void handleCancelar(ActionEvent event) {
        limpiarCampos();
        mostrarMensajeInfo("La operación fue cancelada.");
    }

    
    
    private boolean validarCampos(String nombreCompleto, String email, String nombreUsuario, String contrasena, String rol, String departamento) {
    StringBuilder errores = new StringBuilder();

    // Validar nombre completo
    if (nombreCompleto == null || nombreCompleto.isEmpty() || nombreCompleto.length() < 3 || nombreCompleto.length() > 100) {
        errores.append("El nombre completo debe contener entre 3 y 100 caracteres.\n");
    }

    // Validar email
    if (email == null || !email.matches("[^@]+@[^.]+\\..+")) {
        errores.append("Debe proporcionar un correo electrónico válido.\n");
    }

    // Validar nombre de usuario
    if (nombreUsuario == null || nombreUsuario.isEmpty() || nombreUsuario.length() < 5 || nombreUsuario.length() > 30) {
        errores.append("El nombre de usuario debe contener entre 5 y 30 caracteres.\n");
    }

    // Validar contraseña
    if (contrasena == null || !contrasena.matches("(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}")) {
        errores.append("La contraseña debe tener al menos 8 caracteres, una mayúscula, un número y un carácter especial.\n");
    }

    
   // Validar rol
    if (rol == null || rol.isEmpty()) {
        errores.append("Debe seleccionar un rol.\n");
    }

    // Validar departamento si el rol es técnico
    if ("Técnico".equals(rol) && (departamento == null || departamento.isEmpty())) {
        errores.append("Debe seleccionar un departamento para el rol Técnico.\n");
    }

    // Mostrar errores si existen
    if (errores.length() > 0) {
        mostrarMensajeError(errores.toString());
        return false; // Indicar que la validación falló
    }

    return true; // Validación exitosa
}

  @FXML
private void handleGuardarUsuario(ActionEvent event) {
    // Obtener los valores ingresados en los campos del formulario
    String nombreCompleto = textNombreCompleto.getText();
    String email = textEmail .getText();
    String nombreUsuario = textNombreUsuario.getText();
    String contrasena = passContrasena.getText();
    String rol = comboBoxRol.getValue();
    String departamento = comboBoxDepartamento.getValue(); 

    // Validar los campos ingresados
    if (!validarCampos(nombreCompleto, email, nombreUsuario, contrasena, rol, departamento)) {
        return; // Salir si hay errores en los datos
    }

         
    // Crear o modificar el usuario
    if (usuarioEnEdicion == null) {
        // Crear un nuevo usuario
        Usuario nuevoUsuario = new Usuario(nombreCompleto, email, nombreUsuario, contrasena, rol, departamento);
        listaUsuarios.add(nuevoUsuario); // Agregar a lista
        mostrarMensajeInfo("El usuario ha sido registrado exitosamente.");
    } else {
        // Modificar el usuario existente
        usuarioEnEdicion.setNombreCompleto(nombreCompleto);
        usuarioEnEdicion.setEmail(email);
        usuarioEnEdicion.setNombreUsuario(nombreUsuario);
        usuarioEnEdicion.setContrasena(contrasena);
        usuarioEnEdicion.setRol(rol);
        usuarioEnEdicion.setDepartamento(departamento);
        mostrarMensajeInfo("La información del usuario ha sido actualizada exitosamente.");
    }
     
    // Redirigir a la interfaz principal
    redirigirAGestionUsuarioVista();
}

  private void redirigirAGestionUsuarioVista() {
    try {
        // Cargar la vista principal (Gestión de Usuarios)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionUsuarioVista.fxml"));
        Parent root = loader.load();

        // Recuperar el controlador de la vista principal
        GestionUsuarioController controller = loader.getController();

        // Pasar la referencia de la lista de usuarios 
        controller.setListaUsuarios(this.listaUsuarios);

        // Cambiar la escena al Stage actual
        Stage stage = (Stage) buttonGuardar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Gestión de Usuarios");
        stage.show();
    } catch (IOException e) {
        mostrarMensajeError("Error al regresar a la vista principal: " + e.getMessage());
        e.printStackTrace();
    }
}




    private void cerrarFormulario() {
        Stage stage = (Stage) buttonGuardar.getScene().getWindow();
        stage.close();
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
    
     @FXML 
     private void handleRegresar (ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/GestionUsuarioVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(GestionUsuarioController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    } 
}