package co.edu.uptc.controllerFx;

import co.edu.uptc.view.menuLoginView;
import javafx.stage.Stage;

public class menuLoginController {
    private Stage primaryStage;
    
    public menuLoginController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void showMainMenu() {
        // Iniciar la vista del menú principal
        menuLoginView mlv = new menuLoginView();
        mlv.start(primaryStage);
    }
}
