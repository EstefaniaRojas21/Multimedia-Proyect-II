package co.edu.uptc.controllerFx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    MoviesManagement movies = new MoviesManagement();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<Movie> listMovies = movies.getMovies();
        ArrayList<String> movieNames = new ArrayList<>();

        if (!listMovies.isEmpty()) {
            // Obtener la primera película en la lista
            Movie firstMovie = listMovies.get(0);
            // Establecer los valores de las etiquetas con los detalles de la primera película
            lblNombre.setText(firstMovie.getName());
            lblAutor.setText(firstMovie.getAuthor());
            lblDesc.setText(firstMovie.getDescription());
            lblDurat.setText(String.valueOf(firstMovie.getDuration()));
        }
    }
    

    @FXML
    void OtherMovie(ActionEvent event) {

    }

    @FXML
    void Play(ActionEvent event) {

    }

    @FXML
    void Return(ActionEvent event) {

    }

}
