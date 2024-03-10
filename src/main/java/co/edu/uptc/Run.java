package co.edu.uptc;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Run extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {

        primaryStage=stage;
        // scene = new Scene(loadFXML("Login"), 640, 480);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uptc/Login.fxml"));
        Parent root = loader.load();
        
        // Obtener las dimensiones del panel raíz
        double width = root.prefWidth(-1);
        double height = root.prefHeight(-1);
        
        // Crear una nueva escena con el panel raíz y establecer las dimensiones obtenidas
        Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Run.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
