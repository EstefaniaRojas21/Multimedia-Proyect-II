package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.Run;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MoviePlayedController {

    @FXML
    private Button btnOtherMovie;

    @FXML
    private Button btnReturn;

    @FXML
    void ChooseOtherMovie(ActionEvent event) throws IOException {
        Run.setRoot("MenuMovies");

    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        Run.setRoot("PlayMovie");
    }

}
