package co.edu.uptc.controllerFx;

import java.io.IOException;
import javafx.scene.Node;
import co.edu.uptc.controller.UserRegisteredController;
import co.edu.uptc.model.UserRegistered;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class menuLoginController {

    private Stage primaryStage;
    
    UserRegisteredController urc = new UserRegisteredController();


    @FXML
    private Button btnMovies;

    @FXML
    private Button btnCategories;

    @FXML
    private Button btnPlayList;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnSeries;

    @FXML
    private void listMovies(ActionEvent event) {
        showMovies();
    }

    // Otros métodos aquí...

    private void showMovies() {
        
        String[] movieNames = urc.getMovieNames();

        if (movieNames.length == 0) {
            // Mostrar un mensaje indicando que no hay películas disponibles
            // Puedes usar una ventana de diálogo o cualquier otro mecanismo de notificación
            return;
        }

        // Lógica para mostrar una tabla con todas las películas
        // Por ejemplo, podrías abrir una nueva ventana con la tabla de películas
        // y mostrar el listado de películas en ella
    }

    @FXML
    private void listCategories(ActionEvent event) {
        // Lógica para manejar el clic en el botón de categorías
    }

    @FXML
    private void listPlay(ActionEvent event) {
        // Lógica para manejar el clic en el botón de lista de reproducción
    }

    @FXML
    private void Return(ActionEvent event) {
        // Lógica para manejar el clic en el botón de regreso
    }

    @FXML
    private void listSeries(ActionEvent event) {
        // Lógica para manejar el clic en el botón de series
    }

    
}

