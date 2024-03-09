package co.edu.uptc.view.admin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuAdminView implements Initializable {

    @FXML
    private HBox panel1;

    @FXML
    private HBox panel2;

    @FXML
    private HBox panel3;

    @FXML
    private HBox panel4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Manejador de eventos de clic para el panel1
        panel1.setOnMouseClicked(event -> {
            System.out.printf("Hola");
        });

        // Manejador de eventos de clic para el panel2
        panel2.setOnMouseClicked(event -> {
            // Lógica para navegar a la segunda interfaz
        });

        // Manejador de eventos de clic para el panel3
        panel3.setOnMouseClicked(event -> {
            // Lógica para navegar a la tercera interfaz
        });

        // Manejador de eventos de clic para el panel4
        panel4.setOnMouseClicked(event -> {
            // Lógica para navegar a la cuarta interfaz
        });
    }
}
