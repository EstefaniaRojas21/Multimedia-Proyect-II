package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Node;
import co.edu.uptc.Run;
import co.edu.uptc.controller.UserRegisteredController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.UserRegistered;
import co.edu.uptc.util.MoviesManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class menuLoginController {

    private Stage primaryStage;
    
    MoviesManagement movies = new MoviesManagement();


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
    private void listMovies(ActionEvent event) throws IOException {
        System.out.println("listMovies method called");

       ArrayList<Movie> listMovies = movies.getMovies();
        
        if (listMovies.size() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("User Movies Menu");
            alert.setHeaderText(null);
            alert.setContentText("There are not movies available");
            alert.showAndWait();
            return;
        }else {
            Run.setRoot("MenuMovies");
        }

        
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

