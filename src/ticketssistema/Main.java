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
      

    
/*    



     @Override
    public void start(Stage primaryStage) throws Exception {
        try {
        // Cargar el archivo FXML                             
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InicioDeSesionVista.fxml"));
         //Cargar la ventanta
        Parent ventana = loader.load();
        
         // Configurar la escena
        Scene scene = new Scene(ventana);
        
        // Crear la escena y mostrar la ventana
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sistema de Gestión");
        primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
    }
    
     public static void main(String[] args) {
        // Iniciar la aplicación JavaFX
         launch(args); 
    }*/

    

       
       /* @Override
    public void start(Stage primaryStage) throws Exception {
         Crear roles y permisos
        List<String> permisosAdmin = Arrays.asList("GestionarRolesPermisos", "ConfigurarParametrosSistema");
        Rol administrador = new Rol("Administrador", permisosAdmin);

        // Crear usuario actual (ejemplo)
        Usuario usuarioActual = new Usuario("AdminUser", administrador);

        
        try {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InicioDeSesionVista.fxml"));
         //Cargar la ventanta
        Parent ventana = loader.load();

        // Obtener el controlador y establecer el usuario actual
        MenuController controller = loader.getController();
        controller.setUsuarioActual(usuarioActual);*/

        
         // Configurar la escena
        //Scene scene = new Scene(ventana);
        
        // Crear la escena y mostrar la ventana
        /*primaryStage.setScene(scene);
        primaryStage.setTitle("Sistema de Gestión");
        primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); 
        }*/
        
    
    
  
        
        
       /* 
           @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar el archivo de vista FXML
            FXMLLoader loader = new FXMLLoader (getClass().getResource("/vista/ConfiguracionSistemaVista.fxml"));
           
            //Cargar la ventanta
            Parent ventana = loader.load();

            // Configurar la escena
            Scene scene = new Scene(ventana);

            // Configurar el escenario (la cual es la ventana principal) y mostrarla
            primaryStage.setTitle("Configuración del Sistema");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    
    
   