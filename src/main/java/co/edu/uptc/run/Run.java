package co.edu.uptc.run;

import co.edu.uptc.controller.UserRegisteredController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Run extends Application {

    public static void main(String[] args) {
        launch(args);
        AppMenus menus = new AppMenus();
        int op = 0;
        while (op != -1) {
            op = switch (op) {
                case 0 -> menus.principalMenu(op);
                case 1 -> menus.logInMenu(op);
                case 2 -> menus.registerMenu(op);
                case 3 -> menus.awayMenu(op);
                case 4 -> menus.administratorMenu(op);
                case 5 -> menus.moviesManagementMenu(op);
                case 6 -> menus.ShowMoviesMenu(op);
                case 7 -> menus.createMoviesMenu(op);
                case 8 -> menus.updateMoviesMenu(op);
                case 9 -> menus.deleteMovieMenu(op);
                case 10 -> menus.SeriesManagementMenu(op);
                case 11 -> menus.createSerieMenu(op);
                case 12 -> menus.ShowSeriesMenu(op);
                case 13 -> menus.updateSeriesMenu(op);
                case 14 -> menus.deleteSeriesMenu(op);
                case 20 -> menus.userRegisteredMenu(op);
                case 21 -> menus.ShowMovies(op);
                case 22 -> menus.ShowSeries(op);
                case 25 -> menus.subscriptionManagerMenu(op);
                case 26 -> menus.seeSubscriptionsMenu();
                case 27 -> menus.createSubscriptionsMenu(op);
                case 28 -> menus.updatesubscriptionMenu(op);
                case 29 -> menus.removeSubscriptionMenu(op);
                case 30 -> menus.playListMenu(op);
                case 31 -> menus.seePlayListMenu(op);
                case 32 -> menus.createPlayListMenu(op);
                case 33 -> menus.updatePlayListMenu(op);
                case 34 -> menus.removePlayListMenu(op);
                case 35 -> menus.categoryMenu(op);
                case 36 -> menus.seeCategorytMenu(op);
                case 37 -> menus.createCategoryMenu(op);
                case 38 -> menus.updateCategoryMenu(op);
                case 39 -> menus.removeCategoryMenu(op);
                case 40 -> menus.userSubMenu(op);
                case 41 -> menus.userCategorytMenu(op);
                default -> op;
            };
        }
    }

    @Override
    public void start(Stage stage) {
        // Create user interface elements
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        Label idLabel = new Label("ID:");
        TextField idField = new TextField();
        idField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                idField.setText(newValue.replaceAll("\\D", ""));
            }
        });

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label confirmPasswordLabel = new Label("Confirm Password:");
        PasswordField confirmPasswordField = new PasswordField();
        Label passwordMessage = new Label("");
        passwordMessage.setTextFill(javafx.scene.paint.Color.RED);

        Button registerButton = new Button("Register");
        registerButton.setPrefSize(80, 30);

        // Create a grid layout to organize the elements
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add elements to the grid layout
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(lastNameField, 1, 1);
        gridPane.add(idLabel, 0, 2);
        gridPane.add(idField, 1, 2);
        gridPane.add(passwordLabel, 0, 3);
        gridPane.add(passwordField, 1, 3);
        gridPane.add(confirmPasswordLabel, 0, 4);
        gridPane.add(confirmPasswordField, 1, 4);
        gridPane.add(passwordMessage, 1, 5);
        gridPane.add(registerButton, 1, 10);

        registerButton.setOnAction(e -> {
            // Verify all fields are completed
            if (nameField.getText().isEmpty() || lastNameField.getText().isEmpty()
                    || idField.getText().isEmpty() || passwordField.getText().isEmpty()
                    || confirmPasswordField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please complete all fields.");
                alert.showAndWait();
                return;
            }

            // Verify password meets requirements
            String password = passwordField.getText();
            if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
                passwordMessage.setText("""
                        Password must contain:
                        - At least 8 characters.
                        - One uppercase letter.
                        - One lowercase letter.
                        - One number.
                        - One special character.""");
                return;
            }

            // Verify passwords match
            if (!passwordField.getText().equals(confirmPasswordField.getText())) {
                passwordMessage.setText("Passwords do not match.");
                return;
            }

            String name = nameField.getText();
            String lastName = lastNameField.getText();
            int id = Integer.parseInt(idField.getText());
            password = passwordField.getText();

            if (new UserRegisteredController().addUser(name, lastName, id, password)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText(String.format("User registered successfully!\nUser: %suptc.edu.co\nPassword: %s",
                        name + id, password));
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(gridPane, 380, 400);
        stage.setTitle("Entertainment Program Registration");
        stage.setScene(scene);
        stage.show();
    }
}
