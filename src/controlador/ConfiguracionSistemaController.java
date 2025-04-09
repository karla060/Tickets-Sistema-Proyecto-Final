/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
        cmBoxIdiomaPred.getItems().addAll("Español", "Inglés", "Ruso");
        comBoxZonaHoraria.getItems().addAll("UTC", "GMT", "CST");
        cmboxNiveles.getItems().addAll("Alta", "Media", "Baja");

        sistemaEmpresa = new SistemaEmpresa();
    }
    
    
    @FXML
    private void handleCargarLogo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Validar el formato del archivo
            String fileName = selectedFile.getName().toLowerCase();
            if (!fileName.endsWith(".jpg") && !fileName.endsWith(".png")) {
                mensajeError2.setText("El logo debe ser un archivo .jpg o .png.");
                return;
            }

            // Validar el tamaño del archivo
            if (selectedFile.length() > 2 * 1024 * 1024) { // 2MB
                mensajeError2.setText("El logo no debe exceder los 2MB.");
                return;
            }

            // Cargar la imagen en el ImageView
            Image image = new Image(selectedFile.toURI().toString());
            imgLogo.setImage(image);
            mensajeError2.setText(""); // Limpiar cualquier mensaje de error previo
        }
    }
    

    @FXML
    private void handleButtonGuardar(ActionEvent event) {
    sistemaEmpresa.setNombreEmpresa(textNombreEmpresa.getText());

    StringBuilder errores = new StringBuilder();

    // Validar el nombre de la empresa
    if (textNombreEmpresa.getText().isEmpty()) {
        errores.append("El nombre de la empresa no puede estar vacío.\n");
    }
        else if ( textNombreEmpresa.getText().length() < 3 || textNombreEmpresa.getText().length() > 100) {
        errores.append("El nombre de la empresa debe tener entre 3 y 100 caracteres.\n");
    }  
        else {
        sistemaEmpresa.setNombreEmpresa(textNombreEmpresa.getText());
    }


    // Validar el logo de la empresa
    if (imgLogo.getImage() == null) {
        errores.append("Debe cargar una imagen para el logo de la empresa.\n");
    } else {
        sistemaEmpresa.setLogoEmpresa(new File(imgLogo.getImage().getUrl()));
    }

    // Validar el idioma predeterminado
    if (cmBoxIdiomaPred.getValue() == null || (!cmBoxIdiomaPred.getValue().equals("Español") && !cmBoxIdiomaPred.getValue().equals("Inglés"))) {
        errores.append("El idioma predeterminado debe ser Español o Inglés.\n");
    } else {
        sistemaEmpresa.setIdiomaPredeterminado(cmBoxIdiomaPred.getValue());
    }

    // Validar la zona horaria
    if (comBoxZonaHoraria.getValue() == null || (!comBoxZonaHoraria.getValue().equals("UTC") && !comBoxZonaHoraria.getValue().equals("GMT") && !comBoxZonaHoraria.getValue().equals("CST"))) {
        errores.append("Debe agregar una zona horaria.\n");
    } else {
        sistemaEmpresa.setZonaHoraria(comBoxZonaHoraria.getValue());
    }

    // Validar el tiempo de vencimiento de los tickets
    if (textTiempoVen.getText().isEmpty()) {
        errores.append("El tiempo de vencimiento de los tickets no puede estar vacío.\n");
    } else {
        try {
            int tiempoVencimiento = Integer.parseInt(textTiempoVen.getText());
            if (tiempoVencimiento < 1 || tiempoVencimiento > 365) {
                errores.append("El tiempo de vencimiento de los tickets debe estar entre 1 y 365 días.\n");
              } else {
                sistemaEmpresa.setTiempoVencimientoTickets(tiempoVencimiento);
            }
        } catch (NumberFormatException e) {
            errores.append("El tiempo de vencimiento de los tickets debe ser un número.\n");
        }
    }

    // Validar los niveles de prioridad
    if (cmboxNiveles.getValue() == null || (!cmboxNiveles.getValue().equals("Alta") && !cmboxNiveles.getValue().equals("Media") && !cmboxNiveles.getValue().equals("Baja"))) {
        errores.append("El nivel de prioridad debe ser Alta, Media o Baja.\n");
    } else {
        sistemaEmpresa.setNivelesPrioridad(cmboxNiveles.getValue());
    }

    // Mostrar mensajes de error si existen
    if (errores.length() > 0) {
        mensajeError.setText(errores.toString());
    } else {
        mensajeError.setText("Configuración guardada exitosamente.");
    }
}


    
     @FXML
    private void handleMenu(ActionEvent event) {
            
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
