package fr.serval;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
        //System.out.println(GlobalKeys.SERVAL_NAME_DESC);
        //Launcher.main();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        Button button = new Button("Click me");

        root.getChildren().add(button);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
