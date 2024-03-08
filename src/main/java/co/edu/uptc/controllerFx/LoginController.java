package co.edu.uptc.controllerFx;

import co.edu.uptc.controller.UserRegisteredController;
import co.edu.uptc.model.Admin;
import co.edu.uptc.view.menuLoginView;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    private Stage primaryStage;

    public LoginController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private Admin admin = new Admin("Elon", "Musk", 1, "Elon1@uptc.admin.co", "1");
    private UserRegisteredController userRegisteredC = new UserRegisteredController();

    public boolean login(TextField userField, PasswordField passwordField) {
        String username = userField.getText();
        String password = passwordField.getText();

        if (admin.couldLogIn(username, password)) {
            if (admin.getPassword().equals(password)) {
                System.out.println("Bienvenido admin");
                return true;
            } else {
                showAlert("Admin password incorrect");
            }
        } else if (userRegisteredC.userFound(username)) {
            if (userRegisteredC.getCurrentUser().couldLogIn(username, password)) {
                if (userRegisteredC.getCurrentUser().getPassword().equals(password)) {
                    System.out.println("Bienvenido usuario registrado");
                    return true;
                } else {
                    showAlert("User Registered password incorrect");
                }
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

    public void showMainMenu() {
        // Crear e iniciar la vista del menú principal
        menuLoginView mlv = new menuLoginView();
        mlv.start(primaryStage);
    }
}



