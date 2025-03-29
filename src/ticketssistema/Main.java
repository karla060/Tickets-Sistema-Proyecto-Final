/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ticketssistema;

import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



/**
 *
 * @author mpelv
 */

public class Main extends Application {
 @Override
    public void start(Stage primaryStage) {

        try {
            // Cargar el archivo de vista FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/SesioonVista.fxml"));
            // Cargar la ventana
            Pane ventana = (Pane) loader.load();
            
             
            // Configurar la escena
            Scene scene = new Scene(ventana);
            
            // Configurar el escenario (la cual es la ventana principal) y mostrarla
            primaryStage.setTitle("Inicio de Sesión");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // Iniciar la aplicación JavaFX
    }
    }
      

    
    
   