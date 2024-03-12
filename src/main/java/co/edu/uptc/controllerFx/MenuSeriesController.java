package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uptc.Run;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import co.edu.uptc.util.SeriesManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class MenuSeriesController implements Initializable{

    @FXML
    private Button btnReturn;

    @FXML
    private ComboBox<String> movieComboBox;

    @FXML
    void Return(ActionEvent event) throws IOException {
        Run.setRoot("MenuLogin.fxml");
    }

    @FXML
    void Select(ActionEvent event) {

    }

    SeriesManagement series = new SeriesManagement();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

       ArrayList<Serie> listSeries = series.getSeries();

            ArrayList<String> serieNames = new ArrayList<>();
            for (Serie serie : listSeries) {
                serieNames.add(serie.getName());
            }

            ObservableList<String> serieNamesList = FXCollections.observableArrayList(serieNames);
            movieComboBox.setItems(serieNamesList);


    }

}
