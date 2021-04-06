module fr.serval {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;

    opens fr.serval to javafx.fxml;

    opens fr.serval.launcher.ihm to javafx.fxml;
    exports fr.serval.launcher.ihm to javafx.graphics;

    opens fr.serval.launcher.plugin.ihm to javafx.fxml, javafx.graphics;
    exports fr.serval.launcher.plugin.ihm to javafx.graphics;
}