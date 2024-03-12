package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uptc.Run;
import co.edu.uptc.controller.UserRegisteredController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.util.MoviesManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MovieController implements Initializable{

    @FXML
    private Button btnOtherMov;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnReturn;

    @FXML
    private Label lblAutor;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblDurat;

    @FXML
    private Label lblNombre;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        MoviesManagement movies = new MoviesManagement();
        ArrayList<Movie> listMovies = movies.getMovies();
        UserRegisteredController userRC = new UserRegisteredController();
        userRC.setMovies(listMovies);

        String a = Prueba.getInstance().nombreMov();
        
        Movie movieselected = userRC.GuardarPelicula(Prueba.getInstance().nombreMov());

        // Movie firstMovie = userRC.GuardarPelicula(Prueba.getInstance().nombreMov());

            lblNombre.setText(movieselected.getName());
            lblAutor.setText(movieselected.getAuthor());
            lblDesc.setText(movieselected.getDescription());
            lblDurat.setText(String.valueOf(movieselected.getDuration()));
    }

    @FXML
    void OtherMovie(ActionEvent event) throws IOException {
        Run.setRoot("MenuMovies");
    }

    @FXML
    void Play(ActionEvent event) throws IOException {
        Run.setRoot("PlayMovie");
    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        Run.setRoot("MenuMovies");
    }

}
