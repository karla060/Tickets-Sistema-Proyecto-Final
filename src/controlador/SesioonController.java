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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Karlaa
 */
public class SesioonController implements Initializable {
     @FXML
    private Button buttonContinuar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
             java.util.logging.Logger.getLogger(SesioonController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }  
    
    
   }
}
