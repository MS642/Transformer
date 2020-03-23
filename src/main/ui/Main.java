package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        TodoWorkouts listWorkouts = new TodoWorkouts();
//        listWorkouts.run();
        launch(args);
    }

    //EFFECTS: sets the parent node from the xml and gives it title, scene and starts it
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("Todo Workout List");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();

    }
}
