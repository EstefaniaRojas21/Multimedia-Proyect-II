package co.edu.uptc.controllerFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uptc.Run;
import co.edu.uptc.model.Movie;
import co.edu.uptc.util.MoviesManagement;

public class PlayMovieController implements Initializable{

    MoviesManagement movies = new MoviesManagement();
    ArrayList<Movie> listMovies = movies.getMovies();

    @FXML
    private Button btnContinue;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnReturn;

    @FXML
    private MediaView video;

    private MediaPlayer mediaPlayer ;

    @FXML
    private Label rutaVideo;

    @FXML
    void Pause(ActionEvent event) {
        mediaPlayer.pause();

    }

    @FXML
    void Play(ActionEvent event) {
        mediaPlayer.play();
    }

    @FXML
    void Return(ActionEvent event) {

    }

    @FXML
    void Continue(ActionEvent event) throws IOException {
        Run.setRoot("MoviePlayed");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Ruta del archivo de video
            String videoFile = getClass().getResource("/co/edu/uptc/imgLogin/video.mp4").toExternalForm();
            
            // Carga el archivo de video
            Media media = new Media(videoFile);
            
            // Crea el reproductor de medios
            mediaPlayer = new MediaPlayer(media);
            
            // Asigna el reproductor de medios a la vista de medios
            video.setMediaPlayer(mediaPlayer);
        } catch (Exception e) {
            // Manejo de la excepción
            System.err.println("Error al cargar el video: " + e.getMessage());
        }
    }

    public MoviesManagement getMovies() {
        return movies;
    }
    

    
}
