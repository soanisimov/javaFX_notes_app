package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group group = new Group();
        Scene scene = new Scene(group);


        Parent content = FXMLLoader.load(getClass().getResource("tasks.fxml"));
        BorderPane root = new BorderPane();
        root.setCenter(content);
        group.getChildren().add(root);

        Image image = new Image("/icons/notespng.png");
        primaryStage.getIcons().add(image);

        primaryStage.setTitle("Заметки");

        String css = this.getClass().getResource("style_t.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setResizable(false);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
