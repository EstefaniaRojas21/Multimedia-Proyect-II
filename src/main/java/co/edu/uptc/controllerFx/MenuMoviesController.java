package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import co.edu.uptc.Run;
import co.edu.uptc.controller.UserRegisteredController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.UserRegistered;
import co.edu.uptc.util.MoviesManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;

public class MenuMoviesController implements Initializable{
    UserRegisteredController userRegisteredC = new UserRegisteredController();

    @FXML
    private ComboBox<String> movieComboBox;

    @FXML
    private Button btnReturn;

    @FXML
    void Select(ActionEvent event) throws IOException {

        Run.setRoot("Movie");
    }
   
    MoviesManagement movies = new MoviesManagement();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

       ArrayList<Movie> listMovies = movies.getMovies();
    
            // LocalTime currentLocalTime = LocalTime.now(ZoneId.of("America/Bogota"));
            // if (userRegisteredC.getCurrentUser().getSub() != null) {
            //     if (currentLocalTime.toNanoOfDay() / 1_000_000 >= userRegisteredC.getCurrentUser().getSub()
            //             .getEndTime()) {
            //         userRegisteredC.getCurrentUser().setSub(null);
            //     }
            // }

            ArrayList<String> movieNames = new ArrayList<>();
            for (Movie movie : listMovies) {
                movieNames.add(movie.getName());
            }

            ObservableList<String> movieNamesList = FXCollections.observableArrayList(movieNames);
            movieComboBox.setItems(movieNamesList);


        }
    }


