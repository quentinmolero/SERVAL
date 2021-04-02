package fr.serval;

import fr.serval.launcher.Launcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
//    public static void main(String[] args) {
//        System.out.println(GlobalKeys.SERVAL_NAME_DESC);
//        Launcher.main();
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Button button = new Button("Click me");

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
