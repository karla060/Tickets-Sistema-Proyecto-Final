/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 * FXML Controller class
 *
 * @author Karlaa
 */
public class AgregarNotaTicketController implements Initializable {
    @FXML
    private TextField textNumeroTicket;
    @FXML
    private TextArea textNotaTIcket;
    @FXML
    private Button botonGuardarNota;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonAdjuntarArchivo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void handleFileButtonAction(ActionEvent event) {
        Window window = ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");
        fileChooser.showOpenDialog(window);
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
             java.util.logging.Logger.getLogger(AgregarNotaTicketController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }  
    
    }
    
}
