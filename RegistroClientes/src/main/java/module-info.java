module org.example.registroclientes {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.registroclientes.application to javafx.fxml, javafx.graphics;
    opens org.example.registroclientes.controller to javafx.fxml;
    exports org.example.registroclientes.application;
}