/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.ConfiguracionSistemaDAO;

import modelo.SistemaEmpresa;

/**
 * FXML Controller class
 *
 * @author Karlaa
 */

public class ConfiguracionSistemaController implements Initializable {

    @FXML
    private TextField textNombreEmpresa;
    @FXML
    private ImageView imgLogo;
    @FXML
    private ComboBox<String> cmBoxIdiomaPred;
    @FXML
    private ComboBox<String> comBoxZonaHoraria;
    @FXML
    private TextField textTiempoVen;
    @FXML
    private ComboBox<String> cmboxNiveles;
    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonCargarLogo;
    @FXML
    private Label mensajeError;
    @FXML
    private Label mensajeError2;

   
    private SistemaEmpresa sistemaEmpresa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // Configurar opciones predeterminadas para los ComboBoxes
    cmBoxIdiomaPred.getItems().addAll("Español", "Inglés", "Ruso");
    comBoxZonaHoraria.getItems().addAll("UTC-5", "UTC+0", "UTC+1", "UTC+2", "UTC+9"); // Opciones válidas según tu base de datos
    cmboxNiveles.getItems().addAll("Alta", "Media", "Baja");

    // Consultar configuración persistida desde la base de datos
    SistemaEmpresa cs = ConfiguracionSistemaDAO.obtenerConfiguracion();

    if (cs != null) {
        sistemaEmpresa = cs; // Asignar la configuración al modelo

        // Asignar los valores recuperados a los campos de la interfaz
        textNombreEmpresa.setText(cs.getNombreEmpresa());
        cmBoxIdiomaPred.getSelectionModel().select(cs.getIdiomaPredeterminado());
        comBoxZonaHoraria.getSelectionModel().select(cs.getZonaHoraria());
        textTiempoVen.setText(String.valueOf(cs.getTiempoVencimientoTickets()));
        cmboxNiveles.getSelectionModel().select(cs.getNivelesPrioridad());

        // Cargar el logo en el ImageView si existe en la base de datos
        if (cs.getLogoEmpresa() != null && cs.getLogoEmpresa().length > 0) {
            ByteArrayInputStream bis = new ByteArrayInputStream(cs.getLogoEmpresa());
            Image logo = new Image(bis); // Crear la imagen a partir de los datos binarios
            imgLogo.setImage(logo); // Mostrar la imagen en el ImageView
        }
    } else {
        // Si no hay configuración en la base de datos, inicializar un nuevo objeto
        sistemaEmpresa = new SistemaEmpresa();
    }
}


    @FXML
    public void handleCargarLogo(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();

    // Filtros para limitar la selección a imágenes válidas (JPG o PNG)
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Imágenes JPG o PNG", "*.jpg", "*.png")
    );

    // Mostrar el diálogo para seleccionar una imagen
    File archivoSeleccionado = fileChooser.showOpenDialog(null);

    if (archivoSeleccionado != null) {
        try {
            // Validar el tamaño del archivo (máximo 2MB)
            if (archivoSeleccionado.length() > 2 * 1024 * 1024) { // 2MB
                mensajeError2.setText("Error: El archivo excede el tamaño máximo de 2MB.");
                return;
            }

            // Leer el archivo en un arreglo de bytes y asignarlo al modelo.
            byte[] imageBytes = java.nio.file.Files.readAllBytes(archivoSeleccionado.toPath());
            sistemaEmpresa.setLogoEmpresaBytes(imageBytes);
            
            // Extraer la extensión del archivo para asignarla a tipoLogoEmpresa
            String fileName = archivoSeleccionado.getName();
            String extension = "";
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                extension = fileName.substring(dotIndex + 1).toUpperCase();
            }
            // Validar que la extensión sea JPG o PNG, si no, se asigna un valor por defecto.
            if (!extension.equals("JPG") && !extension.equals("PNG")) {
                extension = "JPG";
            }
            sistemaEmpresa.setTipoLogoEmpresa(extension);
            
            // Cargar la imagen en el ImageView a partir del archivo seleccionado.
            Image logo = new Image(archivoSeleccionado.toURI().toString());
            imgLogo.setImage(logo);

            // Limpiar cualquier mensaje de error previo.
            mensajeError2.setText("");
            System.out.println("Imagen cargada exitosamente.");
        } catch (IOException e) {
            mensajeError2.setText("Error al cargar la imagen: " + e.getMessage());
            e.printStackTrace();
        }
    } else {
        mensajeError2.setText("No se seleccionó ningún archivo.");
    }
}


        private String validarCampos() {
        StringBuilder errores = new StringBuilder();

        // Validar el nombre de la empresa
        String nombreEmpresa = textNombreEmpresa.getText();
        if (nombreEmpresa == null || nombreEmpresa.trim().isEmpty()) {
            errores.append("El nombre de la empresa no puede estar vacío.\n");
        } else if (nombreEmpresa.length() < 3 || nombreEmpresa.length() > 100) {
            errores.append("El nombre de la empresa debe tener entre 3 y 100 caracteres.\n");
        }

        // Validar el logo de la empresa: ahora se verifica la imagen del ImageView.
        if (imgLogo.getImage() == null) {
            errores.append("Debe cargar una imagen para el logo de la empresa.\n");
        }

        // Validar el idioma predeterminado
        String idiomaPredeterminado = cmBoxIdiomaPred.getValue();
        if (idiomaPredeterminado == null ||
            (!idiomaPredeterminado.equals("Español") && !idiomaPredeterminado.equals("Inglés"))) {
            errores.append("El idioma predeterminado debe ser Español o Inglés.\n");
        }

        // Validar la zona horaria
        String zonaHoraria = comBoxZonaHoraria.getValue();
        if (zonaHoraria == null) {
            errores.append("Debe agregar una zona horaria.\n");
        }

        // Validar el tiempo de vencimiento de los tickets
        String tiempoVencimientoStr = textTiempoVen.getText();
        if (tiempoVencimientoStr == null || tiempoVencimientoStr.trim().isEmpty()) {
            errores.append("El tiempo de vencimiento de los tickets no puede estar vacío.\n");
        } else {
            try {
                int tiempoVencimiento = Integer.parseInt(tiempoVencimientoStr);
                if (tiempoVencimiento < 1 || tiempoVencimiento > 365) {
                    errores.append("El tiempo de vencimiento de los tickets debe estar entre 1 y 365 días.\n");
                }
            } catch (NumberFormatException e) {
                errores.append("El tiempo de vencimiento de los tickets debe ser un número.\n");
            }
        }

        // Validar los niveles de prioridad
        String nivelesPrioridad = cmboxNiveles.getValue();
        if (nivelesPrioridad == null ||
            (!nivelesPrioridad.equals("Alta") && !nivelesPrioridad.equals("Media") && !nivelesPrioridad.equals("Baja"))) {
            errores.append("El nivel de prioridad debe ser Alta, Media o Baja.\n");
        }

        return errores.toString();
    }

    @FXML
    private void handleButtonGuardar(ActionEvent event) {
        // Validar los campos antes de guardar
        String errores = validarCampos();
        if (!errores.isEmpty()) {
            mensajeError.setText(errores);
            return; // Detener ejecución si hay errores
        }

        // Asignar los valores de las celdas del formulario al modelo
        sistemaEmpresa.setNombreEmpresa(textNombreEmpresa.getText());
        sistemaEmpresa.setIdiomaPredeterminado(cmBoxIdiomaPred.getValue());
        sistemaEmpresa.setZonaHoraria(comBoxZonaHoraria.getValue());
        sistemaEmpresa.setTiempoVencimientoTickets(Integer.parseInt(textTiempoVen.getText()));
        sistemaEmpresa.setNivelesPrioridad(cmboxNiveles.getValue());
        // NOTA: La imagen ya se asignó en handleCargarLogo (como byte[])

        // Persistir la configuración en la base de datos utilizando el DAO
        boolean guardado = modelo.ConfiguracionSistemaDAO.guardarConfiguracion(sistemaEmpresa);
        if (guardado) {
            mensajeError.setText("Configuración guardada correctamente en la base de datos.");
        } else {
            mensajeError.setText("Error al guardar la configuración en la base de datos.");
        }
    }
    
    @FXML
    public void handleMenu(ActionEvent event) {
            
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));
             Parent root;
             root = loader.load();
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
             
         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(ConfiguracionSistemaController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }
}
