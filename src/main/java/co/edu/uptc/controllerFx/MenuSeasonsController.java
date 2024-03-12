package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uptc.Run;
import co.edu.uptc.controller.UserRegisteredController;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.util.SeriesManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class MenuSeasonsController implements Initializable{
    UserRegisteredController userRegisteredC = new UserRegisteredController();

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnReturn;

    @FXML
    private ComboBox<String> seasonComboBox;

    @FXML
    void Return(ActionEvent event) throws IOException {
        Run.setRoot("MenuLogin");
    }

    SeriesManagement series = new SeriesManagement();
    int selectionCount = 0;

    @FXML
    void Select(ActionEvent event) {
        if (selectionCount == 0) {
            return;
        } 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Serie> listSeries = series.getSeries();
        ArrayList<String> serieSeason = new ArrayList<>();

        for (Serie serie : listSeries) {
            ArrayList<Season> seasons = serie.getSeasons();
            for (Season season : seasons) {
                serieSeason.add(season.getSeasonName()); // Suponiendo que hay un método getName() en la clase Season que devuelve el nombre de la temporada
            }

        ObservableList<String> seasonNamesList = FXCollections.observableArrayList(serieSeason);
        seasonComboBox.setItems(seasonNamesList);


        seasonComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectionCount++; // Incrementar el contador de selecciones
            if (selectionCount == 1) {
                // Seleccionado por primera vez
                Prueba.getInstance().GuardarSeason(newValue);        
            }
        });
    }
    }

    @FXML
    void SelectionMovie(ActionEvent event) throws IOException {
        Run.setRoot("Serie");
    }

}
