/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mpelv
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
    
    
    /**
     * Initializes the controller class.
     */
 
       @Override
    public void initialize(URL url, ResourceBundle rb) {
      // Inicializar ComboBox de idioma predeterminado
        cmBoxIdiomaPred.getItems().addAll("Español", "Inglés", "Ruso");

        // Inicializar ComboBox de zona horaria
        comBoxZonaHoraria.getItems().addAll("Guatemala-6", "Guatemala-5", "Guatemala-4");

        // Inicializar ComboBox de niveles de prioridad
        cmboxNiveles.getItems().addAll("Alta", "Media", "Baja");   
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