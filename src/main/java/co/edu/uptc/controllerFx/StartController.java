package co.edu.uptc.controllerFx;

import java.io.IOException;

import co.edu.uptc.Run;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnVisitor;

    @FXML
    void Login(ActionEvent event) throws IOException {
        Run.setRoot("Login");
    }

    @FXML
    void Register(ActionEvent event) {
        
    }

    @FXML
    void Visitor(ActionEvent event) {

    }

}
