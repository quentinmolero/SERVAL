module fr.serval {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens fr.serval to javafx.fxml;
    opens fr.serval.controller to javafx.fxml;
    exports fr.serval;
}