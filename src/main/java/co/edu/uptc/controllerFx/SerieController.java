package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uptc.Run;
import co.edu.uptc.controller.UserRegisteredController;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Serie;
import co.edu.uptc.util.MoviesManagement;
import co.edu.uptc.util.SeriesManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SerieController implements Initializable{

    @FXML
    private Button btnOtherSer;

    @FXML
    private Button btnSeeSeasons;

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

        SeriesManagement series = new SeriesManagement();
        ArrayList<Serie> listSeries = series.getSeries();
        UserRegisteredController userRC = new UserRegisteredController();
        userRC.setSeries(listSeries);

        String a = Prueba.getInstance().nombreSer();
        
        Serie serieselected = userRC.GuardarSerie(Prueba.getInstance().nombreSer());
            lblNombre.setText(serieselected.getName());
            lblAutor.setText(serieselected.getAuthor());
            lblDesc.setText(serieselected.getDescription());
            lblDurat.setText(String.valueOf(serieselected.getDuration()));
    }

    @FXML
    void OtherSerie(ActionEvent event) throws IOException {
        Run.setRoot("MenuSeries");

    }

    @FXML
    void SeeSeason(ActionEvent event) {

    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        Run.setRoot("MenuSeries");
    }

}
