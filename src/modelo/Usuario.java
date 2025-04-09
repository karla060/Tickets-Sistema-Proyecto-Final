/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Karlaa
 */


public class Usuario {
    private String nombreCompleto; // Nombre completo del usuario
    private String email;          // Correo electrónico del usuario
    private String nombreUsuario;  // Nombre de usuario único
    private String contrasena;     // Contraseña del usuario
    private String rol;            // Rol asignado al usuario (Administrador, Técnico, etc.)
    private String departamento;   // Departamento del usuario (opcional, solo para Técnicos)

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(String nombreCompleto, String email, String nombreUsuario, String contrasena, String rol, String departamento) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.departamento = departamento; // Puede ser null o vacío si no aplica
    }

    // Getters y Setters
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    // Método para mostrar información del usuario (opcional para depuración)
    @Override
    public String toString() {
        return String.format(
            "Usuario: %s, Email: %s, Nombre de Usuario: %s, Rol: %s, Departamento: %s",
            nombreCompleto, email, nombreUsuario, rol, (departamento == null || departamento.isEmpty() ? "N/A" : departamento)
        );
    }
}



/*
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class GestionUsuariosController {

    // Campos de formulario
    @FXML
    private TextField txtNombreCompleto;
    @FXML
    private TextField txtCorreoElectronico;
    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private ComboBox<String> cbRoles;
    @FXML
    private ComboBox<String> cbDepartamentos;

    // Tabla de usuarios existentes
    @FXML
    private TableView<Usuario> tblUsuarios;
    @FXML
    private TableColumn<Usuario, String> colNombreUsuario;
    @FXML
    private TableColumn<Usuario, String> colCorreoElectronico;
    @FXML
    private TableColumn<Usuario, String> colRol;
    @FXML
    private TableColumn<Usuario, String> colDepartamento;

    private ObservableList<Usuario> usuarios;

    @FXML
    public void initialize() {
        // Inicializar lista de usuarios
        usuarios = FXCollections.observableArrayList();
        tblUsuarios.setItems(usuarios);

        // Configurar columnas de la tabla
        colNombreUsuario.setCellValueFactory(data -> data.getValue().nombreUsuarioProperty());
        colCorreoElectronico.setCellValueFactory(data -> data.getValue().correoElectronicoProperty());
        colRol.setCellValueFactory(data -> data.getValue().rolAsignadoProperty());
        colDepartamento.setCellValueFactory(data -> data.getValue().departamentoProperty());

        // Configurar opciones de roles y departamentos
        cbRoles.setItems(FXCollections.observableArrayList("Administrador", "Técnico", "Usuario Regular"));
        cbDepartamentos.setItems(FXCollections.observableArrayList("TI", "Recursos Humanos", "Ventas"));
    }

    @FXML
    private void handleGuardarUsuario(ActionEvent event) {
        String nombreCompleto = txtNombreCompleto.getText();
        String correoElectronico = txtCorreoElectronico.getText();
        String nombreUsuario = txtNombreUsuario.getText();
        String contrasena = txtContrasena.getText();
        String rolAsignado = cbRoles.getValue();
        String departamento = cbDepartamentos.getValue();

        // Validaciones de campos
        if (nombreCompleto == null || nombreCompleto.length() < 3 || nombreCompleto.length() > 100) {
            mostrarMensajeError("El nombre completo debe contener entre 3 y 100 caracteres.");
            return;
        }

        if (correoElectronico == null || !correoElectronico.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            mostrarMensajeError("Debe ingresar un correo electrónico válido.");
            return;
        }

        if (nombreUsuario == null || nombreUsuario.length() < 5 || nombreUsuario.length() > 30) {
            mostrarMensajeError("El nombre de usuario debe contener entre 5 y 30 caracteres.");
            return;
        }

        if (!esNombreUsuarioUnico(nombreUsuario)) {
            mostrarMensajeError("El nombre de usuario ya está registrado.");
            return;
        }

        if (contrasena == null || !contrasena.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            mostrarMensajeError("La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, un número y un carácter especial.");
            return;
        }

        if (rolAsignado == null) {
            mostrarMensajeError("Debe asignar un rol.");
            return;
        }

        if ("Técnico".equals(rolAsignado) && (departamento == null || departamento.isEmpty())) {
            mostrarMensajeError("Debe seleccionar un departamento para el usuario técnico.");
            return;
        }

        // Registrar usuario
        Usuario nuevoUsuario = new Usuario(nombreCompleto, correoElectronico, nombreUsuario, contrasena, rolAsignado, departamento);
        usuarios.add(nuevoUsuario);
        mostrarMensajeInfo("Usuario registrado exitosamente.");
        limpiarFormulario();
    }

    @FXML
    private void handleModificarUsuario(ActionEvent event) {
        Usuario usuarioSeleccionado = tblUsuarios.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado == null) {
            mostrarMensajeError("Debe seleccionar un usuario para modificar.");
            return;
        }

        // Modificar información del usuario
        usuarioSeleccionado.setNombreCompleto(txtNombreCompleto.getText());
        usuarioSeleccionado.setCorreoElectronico(txtCorreoElectronico.getText());
        usuarioSeleccionado.setNombreUsuario(txtNombreUsuario.getText());
        usuarioSeleccionado.setContrasena(txtContrasena.getText());
        usuarioSeleccionado.setRolAsignado(cbRoles.getValue());
        usuarioSeleccionado.setDepartamento(cbDepartamentos.getValue());

        tblUsuarios.refresh();
        mostrarMensajeInfo("Usuario modificado exitosamente.");
        limpiarFormulario();
    }

    @FXML
    private void handleDesactivarUsuario(ActionEvent event) {
        Usuario usuarioSeleccionado = tblUsuarios.getSelectionModel().getSelectedItem();

        if (usuarioSeleccionado == null) {
            mostrarMensajeError("Debe seleccionar un usuario para desactivar.");
            return;
        }

        // Desactivar usuario
        usuarioSeleccionado.setActivo(false);
        tblUsuarios.refresh();
        mostrarMensajeInfo("Usuario desactivado exitosamente.");
    }

    @FXML
    private void handleCancelarRegistro(ActionEvent event) {
        limpiarFormulario();
        mostrarMensajeInfo("El registro de usuario ha sido cancelado.");
    }

    // Métodos auxiliares
    private boolean esNombreUsuarioUnico(String nombreUsuario) {
        return usuarios.stream().noneMatch(usuario -> usuario.getNombreUsuario().equals(nombreUsuario));
    }

    private void limpiarFormulario() {
        txtNombreCompleto.clear();
        txtCorreoElectronico.clear();
        txtNombreUsuario.clear();
        txtContrasena.clear();
        cbRoles.setValue(null);
        cbDepartamentos.setValue(null);
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

*/