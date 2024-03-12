package co.edu.uptc.controllerFx;

import java.io.IOException;
import java.net.URL;
import javafx.scene.Node;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import co.edu.uptc.Run;
import co.edu.uptc.controller.UserRegisteredController;
import co.edu.uptc.model.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    private Stage primaryStage;

    @FXML
    private Button btnLogin;

    @FXML
    private Label btnRegister;

    @FXML
    private Button btnReturn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    public void Login(ActionEvent event) throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();
        if (login(username, password)) {
            showMainMenu(event);
        }
    }

    private boolean login(String username, String password) {
        Admin admin = new Admin("Elon", "Musk", 1, "Elon1@uptc.admin.co", "1");
        UserRegisteredController userRegisteredC = new UserRegisteredController();

        if (userRegisteredC.userFound(username)) {
            if (userRegisteredC.getCurrentUser().couldLogIn(username, password)) {
                if (userRegisteredC.getCurrentUser().getPassword().equals(password)) {
                    return true;
                } else {
                    showAlert("User Registered password incorrect");
                }
            }
            
        } else if (admin.couldLogIn(username, password)) {
            
            if (admin.getPassword().equals(password)) {
                return true;
            } else {
                showAlert("Admin password incorrect");
            }
        } else {
            showAlert("User not found!");
        }
        return false;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showMainMenu(ActionEvent event) throws IOException {

        Run.setRoot("MenuLogin");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void Select(ActionEvent event) {
        //Crear una cuenta
    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        Run.setRoot("Start");
    }
}