module fr.serval {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens fr.serval to javafx.fxml;
    exports fr.serval;
}