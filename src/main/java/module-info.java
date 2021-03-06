module fr.serval {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires json.simple;

    opens fr.serval to javafx.fxml;

    opens fr.serval.launcher.ihm to javafx.fxml;
    exports fr.serval.launcher.ihm to javafx.graphics;

    opens fr.serval.launcher.plugin.ihm to javafx.fxml, javafx.graphics;
    exports fr.serval.launcher.plugin.ihm to javafx.graphics;

    opens fr.serval.application.ihm to javafx.fxml, javafx.graphics;

    exports fr.serval.application.ihm;
    exports fr.serval.controller;
    exports fr.serval.ihm;
    exports fr.serval.application.project;
    exports fr.serval.tools;
}