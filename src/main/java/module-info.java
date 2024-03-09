module co.edu.uptc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;

    opens co.edu.uptc.model to javafx.base;
    opens co.edu.uptc.view to javafx.fxml;
    opens co.edu.uptc.view.admin to javafx.fxml; // Agregar esta línea
    exports co.edu.uptc.view.admin;
    exports co.edu.uptc.view;
}
