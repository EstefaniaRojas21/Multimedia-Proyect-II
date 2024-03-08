package co.edu.uptc.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class menuLoginView {
    
    public void start(Stage primaryStage) {
        // Crear la interfaz de usuario del menú principal
        BorderPane root = new BorderPane();
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        
        // Crear los botones del menú principal
        Button moviesButton = new Button("Movies");
        Button seriesButton = new Button("Series");
        Button playlistButton = new Button("Playlist Management");
        Button categoriesButton = new Button("Categories");
        Button subscriptionsButton = new Button("Subscriptions");
        Button returnButton = new Button("Return");

        // Agregar los botones al VBox
        vbox.getChildren().addAll(moviesButton, seriesButton, playlistButton, categoriesButton, subscriptionsButton, returnButton);
        
        // Agregar el VBox al centro del BorderPane
        root.setCenter(vbox);
        
        // Configurar la escena
        Scene scene = new Scene(root, 600, 400);
        
        // Asignar la escena al escenario
        primaryStage.setScene(scene);
        
        // Mostrar la ventana
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }
}
