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
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.UserRegistered;
import co.edu.uptc.util.MoviesManagement;
import co.edu.uptc.util.SeriesManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;

public class MenuSeriesController implements Initializable{
    UserRegisteredController userRegisteredC = new UserRegisteredController();

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnReturn;

    @FXML
    private ComboBox<String> serieComboBox;

    @FXML
    void Return(ActionEvent event) throws IOException {
        Run.setRoot("MenuLogin");
    }

    SeriesManagement series = new SeriesManagement();
    int selectionCount = 0;

    @FXML
    void Select(ActionEvent event) throws IOException {
        if (selectionCount == 0) {
            return;
        } 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Serie> listSeries = series.getSeries();
        ArrayList<String> serieNames = new ArrayList<>();

        for (Serie serie : listSeries) {
            serieNames.add(serie.getName());
        }

        ObservableList<String> serieNamesList = FXCollections.observableArrayList(serieNames);
        serieComboBox.setItems(serieNamesList);


        serieComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectionCount++; // Incrementar el contador de selecciones
            if (selectionCount == 1) {
                // Seleccionado por primera vez
                Prueba.getInstance().GuardarSerie(newValue);        
            }
        });
    }


    @FXML
    void SelectionMovie(ActionEvent event) throws IOException {
        Run.setRoot("Serie");
    }

}
